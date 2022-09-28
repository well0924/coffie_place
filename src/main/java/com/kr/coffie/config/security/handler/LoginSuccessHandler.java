package com.kr.coffie.config.security.handler;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler{ 

	private RequestCache requestCache = new HttpSessionRequestCache();
	private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();
	private static final String DEFAULT_URL= "/page/main/mainpage";
	private static final String ADMIN_URL="/page/admin/adminlist";
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		try {
			redirectStrategy(request, response, authentication);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clearAuthenticationAttributes(request);
	}
	
	private void clearAuthenticationAttributes(HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		
		if(session !=null) {
	
			session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		
		}
	}
	
	private void redirectStrategy(HttpServletRequest request,HttpServletResponse response,Authentication authentication)throws Exception {
		
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		if(savedRequest != null) {
		
			redirectStratgy.sendRedirect(request, response, DEFAULT_URL);
		
		}else {
			
			Set<String>roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
			
			if(roles.contains("ROLE_ADMIN")) {
				
				redirectStratgy.sendRedirect(request, response,ADMIN_URL);
			
			}else if(roles.contains("ROLE_USER")) {
				
				redirectStratgy.sendRedirect(request, response,DEFAULT_URL);
			
			}else {
			
				redirectStratgy.sendRedirect(request, response,DEFAULT_URL);
			
			}
		}
	}
}
