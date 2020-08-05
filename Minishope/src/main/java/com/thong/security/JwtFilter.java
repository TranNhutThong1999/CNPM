package com.thong.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.thong.DTO.MyUser;
import com.thong.Entity.User;
import com.thong.InterfaceService.IUserService;
import com.thong.JWT.JWT;

public class JwtFilter extends GenericFilterBean {
	@Autowired
	private JWT jWT;
	@Autowired
	private IUserService nhanVienService;
	
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
        throws IOException, ServletException {
    	System.out.println("vao filter ----------------------------------------------");
    	HttpServletRequest r = (HttpServletRequest) req;
        String token = r.getHeader("Authorization");
        if (token != null && jWT.validateToken(token)) {
        	String usename =jWT.getUserNameFromJWT(token);
        	User nv = nhanVienService.findByUserName(usename);
        	
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
        filterChain.doFilter(req, res);
    }

	
}
