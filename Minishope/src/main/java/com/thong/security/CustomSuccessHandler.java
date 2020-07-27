package com.thong.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.thong.DTO.MyUser;
import com.thong.Service.CustomUserDetail;
import com.thong.Util.SecurityUtil;


@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler  {
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	public void handle(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication,ModelMap m) throws IOException, ServletException {
		String targetUrl = determineTargetUrl(authentication);
		if (response.isCommitted()) {
			return;
		}
		
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
		
	private String determineTargetUrl(Authentication authentication) {
		// TODO Auto-generated method stub
		List<String> roles = SecurityUtil.getAuthortities();
		String url = "";
		if (roles.contains("ROLE_admin")) {
			url = "/Admin-SanPham/";
		} else if (roles.contains("ROLE_user")) {
			url = "/Home";
		}
		return url;
	}

	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	
	}


