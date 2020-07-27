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
import com.thong.DTO.NhanVienDTO;
import com.thong.Entity.NhanVien;
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


	private boolean createPrincical(NhanVien nv) {
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
	public ResponseEntity<String> testValid(@RequestParam  String username,@RequestParam String password) {
		
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
}
