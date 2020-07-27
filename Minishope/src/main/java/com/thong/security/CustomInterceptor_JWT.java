package com.thong.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.thong.DTO.MyUser;
import com.thong.Entity.NhanVien;
import com.thong.InterfaceService.INhanVienService;
import com.thong.JWT.JWT;

public class CustomInterceptor_JWT extends HandlerInterceptorAdapter {

	@Autowired
	private JWT jWT;
	
	@Autowired
	private INhanVienService nhanVienService;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("CustomInterceptor");
		System.out.println(request.getHeader("Authorization"));
		String token = request.getHeader("Authorization");
		System.out.println("token:"+token);
		if (jWT.validateToken(token)&& token!=null) {
			String usename =jWT.getUserNameFromJWT(token);
        	NhanVien nv = nhanVienService.findByUserName(usename);
        	
        	List<GrantedAuthority> authortity = new ArrayList<GrantedAuthority>();
    		authortity.add(new SimpleGrantedAuthority(nv.getChucVu().getTenChucVu()));
        	MyUser user = new MyUser(nv.getTenDangNhap(), nv.getMatKhau(), nv.isEnabled(), true, true, nv.isNonBanned(), authortity);
    		user.setCMND(nv.getCMND());
    		user.setDiaChi(nv.getDiaChi());
    		user.setEmail(nv.getEmail());
    		user.setGioiTinh(nv.getGioiTinh());
    		user.setHoTen(nv.getHoTen());
    		user.setSoDT(nv.getSoDT());
        	UsernamePasswordAuthenticationToken auth =new UsernamePasswordAuthenticationToken(user, null);
        	SecurityContextHolder.getContext().setAuthentication(auth);
		}
		//throw new TokenFail();
		return true;
	}

	private boolean createPrincical(NhanVien nv) {

		List<GrantedAuthority> listAuthor = new ArrayList<GrantedAuthority>();
		listAuthor.add(new SimpleGrantedAuthority(nv.getChucVu().getTenChucVu()));
		MyUser user;
		user = new MyUser(nv.getTenDangNhap(), nv.getMatKhau(), nv.isEnabled(), true, true, nv.isNonBanned(), listAuthor);

		user.setEmail(nv.getEmail());
		user.setHoTen(nv.getHoTen());
		// UserDetails userDetails = user;
		System.out.println(nv.getChucVu().getTenChucVu());
		if (user.isEnabled() == false || user.isAccountNonLocked() == false) {
			return false;
		}
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null,
				user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
		return true;
	}
}
