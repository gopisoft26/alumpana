package com.alumpana.resource.management.spring.config;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class MySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	protected Log logger = LogFactory.getLog(this.getClass());
	 
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		 handle(request, response, authentication);
	        clearAuthenticationAttributes(request);
	}
	
	protected void handle(HttpServletRequest request, 
		      HttpServletResponse response, Authentication authentication)
		      throws IOException {
		  
		        String targetUrl = determineTargetUrl(authentication);
		 
		        if (response.isCommitted()) {
		            logger.debug(
		              "Response has already been committed. Unable to redirect to "
		              + targetUrl);
		            return;
		        }
		 
		        redirectStrategy.sendRedirect(request, response, targetUrl);
		    }
		 
	protected String determineTargetUrl(Authentication authentication) {
		
		boolean isUser 		= false;
		boolean isAdmin 	= false;
		boolean isStudent 	= false;
		boolean isStaff   	= false;
		boolean isParent  	= false;
		boolean isHod     	= false;
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		
		for (GrantedAuthority grantedAuthority : authorities) {
			
			String role = grantedAuthority.getAuthority();
		
			logger.info(" Role " +role);
			
			if (role.equals("ROLE_USER")) {
				isUser = true;
				break;
			} else if (role.equals("ROLE_ADMIN")) {
				isAdmin = true;
				break;
			} else if (role.equals("ROLE_STUDENT")) {
				isStudent = true;
				break;
			} else if (role.equals("ROLE_PARENT")) {
				isParent = true;
				break;
			} else if (role.equals("ROLE_STAFF")) {
				isStaff = true;
				break;
			} else if (role.equals("ROLE_HOD")) {
				isHod = true;
				break;
			}
		}

		String page = "/login";

		if (isUser) {
			page = "/user";
		} else if (isAdmin) {
			page = "/admin";
		} else if (isStudent) {
			page = "/student";
		} else if (isParent) {
			page = "/parent";
		}  else if (isStaff) {
			page = "/staff";
		} else if (isHod) {
			page = "/hod";
		} else {
			logger.info("unkown role ");
			throw new IllegalStateException();
		}

		logger.info("loaded " +page);
		return page;
		
	}
	
	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

}
