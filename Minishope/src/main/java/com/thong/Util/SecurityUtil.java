package com.thong.Util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import com.thong.DTO.MyUser;

public class SecurityUtil {

	public static List<String> getAuthortities(){
		List<String> result = new ArrayList<String>();
		List<GrantedAuthority> listAuthor = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		for(GrantedAuthority g :listAuthor) {
			result.add(g.getAuthority());
		}
		return result;
	}	
//	public static void logOutUser(String userName,HttpServletRequest re,HttpServletResponse res) {
//		MyUser user =(MyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		if(user.getUsername().equals(userName)) {
//			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//			//if (auth != null) {
//			//System.out.println(auth.getCredentials());
//			 //   new SecurityContextLogoutHandler().logout(re, res, auth);
//		//	}
//		}
//	}
	//public static boolean 
}
