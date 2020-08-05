package com.thong.Ajax;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.thong.DTO.MyUser;
import com.thong.DTO.UserDTO;
import com.thong.Entity.ChucVu;
import com.thong.Entity.User;
import com.thong.InterfaceService.IChucVuService;
import com.thong.InterfaceService.INhanVienService;
import com.thong.JWT.JWT;
import com.thong.Service.MailSerive;

@RestController
@RequestMapping("Api/")
@Validated
public class Ajax {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	private INhanVienService nhanVienService;

	@Autowired
	private ServletContext context;

	@Autowired
	private MessageSource mes;

	@Autowired // Asysc
	private MailSerive mailSerive;

	@Autowired
	private BCryptPasswordEncoder bCrypt;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JWT jwt;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private IChucVuService chucVuService;
//signUp
	@PostMapping(value = "CheckSignUp/", produces = "Application/json;charset=UTF-8")
	public String logInProccess(@RequestBody @Valid UserDTO nv, BindingResult bindingResult) {
		JSONObject json = new JSONObject();
		json.put("tenDangNhap", "");
		json.put("email", "");
		json.put("matKhau", "");
		if (bindingResult.hasErrors()) {
			for (FieldError o : bindingResult.getFieldErrors()) {
				json.put(o.getField(), o.getDefaultMessage());
			}
			return json.toString();
		}

		return "";
	}

	static boolean isValidEmail(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}

	static boolean isValidUserName(String tenDangNhap) {
		String regex = "^[a-zA-Z]+[0-9]+";
		return tenDangNhap.matches(regex);
	}

	static boolean isValidMatKhau(String matKhau) {
		String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,20}$";
		return matKhau.matches(regex);
	}
	

	@PostMapping(value = "login-Facebook", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> LoginByFaceBook(@RequestBody String json) {
		boolean result = false;
		JSONObject o = new JSONObject(json);
		User nv = nhanVienService.findByUserName(o.getString("userID"));
		String JWT;
		if (nv == null) {
			// create user
			System.out.println("vao");
			User nvFB = new User();
			nvFB.setTenDangNhap(o.getString("userID"));
			nvFB.setHoTen(o.getString("firstname") + " " + o.getString("lastname"));
			nvFB.setToKenFB(o.getString("token"));
			if(o.getString("email")!=null) {
			nvFB.setEmail(o.getString("email"));
			}
			nvFB.setEnabled(true);
			nvFB.setNonBanned(true);
			ChucVu cv = chucVuService.findOneByName("ROLE_user");
			nvFB.setChucVu(cv);
			System.out.println(nvFB.getChucVu().toString());
			nhanVienService.saveUserFB(nvFB);
			createPrincical(nvFB);
			JWT = jwt.generateToken(nvFB.getTenDangNhap());
			return new ResponseEntity<String>(JWT, HttpStatus.OK);
		} else {
			// create principal
			result = createPrincical(nv);
			if (result == false) {
				System.out.println("bedddddd");
				return new ResponseEntity<String>("failure", HttpStatus.BAD_REQUEST);
			}
			JWT = jwt.generateToken(nv.getTenDangNhap());
			return new ResponseEntity<String>(JWT, HttpStatus.OK);
		}

	}
	private boolean createPrincical(User nv) {
		List<GrantedAuthority> listAuthor = new ArrayList<GrantedAuthority>();
		listAuthor.add(new SimpleGrantedAuthority(nv.getChucVu().getTenChucVu()));
		MyUser user;
		user = new MyUser(nv.getTenDangNhap(), "", nv.isEnabled(), true, true, nv.isNonBanned(), listAuthor);

		user.setEmail(nv.getEmail());
		user.setHoTen(nv.getHoTen());
		// UserDetails userDetails = user;
		if (user.isEnabled() == false || user.isAccountNonLocked() == false) {
			return false;
		}
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null,
				user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
		System.out.println("principal");
		return true;
	}

	@PostMapping("/login")
	public ResponseEntity<String> checkLogin(@RequestParam  String username,@RequestParam String password) {
		
		System.out.println(username);
		System.out.println(password);
		try {
			Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
			SecurityContextHolder.getContext().setAuthentication(auth);
			String JWT=jwt.generateToken(username);
			return new ResponseEntity<String>(JWT,HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<String>("failure",HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping(value = "sendTokenPassword", produces = "text/phain;charset=UTF-8")
	public String sendTokenPassword( @RequestParam String userName,@RequestParam String url) {
		System.out.println(url);
		UserDTO nv = nhanVienService.findByUserNameDTO(userName);
		if (nv != null) {
			nv.setTokenRamdom();
			nv.setTimeTokenFuture(15);
			nhanVienService.update(nv);
			mailSerive.sendMail(nv.getEmail(), "Verify create account",
					url+"/Minishope/login?token=" + nv.getToken());
			return "ok";
		} else {
			return "Tên Đăng Nhập không tồn tại";
		}
	}

	
}
