package com.luv2code.springsecurity.demo.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.luv2code.springsecurity.demo.entity.User;
import com.luv2code.springsecurity.demo.service.UserService;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		String userName = authentication.getName();
		User theUser = userService.findByUserName(userName);
		
		// now place in the session
		HttpSession session = request.getSession();
		session.setAttribute("user", theUser);
		
		
		// pages for roles
		
		if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CUSTOMER"))) {
			response.sendRedirect(request.getContextPath() + "/customer/information");
		}else if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"))) {
			response.sendRedirect(request.getContextPath() + "/manager/information");
		}else if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			response.sendRedirect(request.getContextPath() + "/admin/information");
		}else if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CHEF"))) {
			response.sendRedirect(request.getContextPath() + "/chef/information");
		}
		
	}

}
