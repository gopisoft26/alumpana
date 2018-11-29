package com.alumpana.resource.management.spring.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.alumpana.resource.management.spring.orm.service.UserProfileService;
import com.alumpana.resource.management.spring.orm.service.UserService;

/**
 * @author Sivakumar ARUMUGAM
 */

@Controller
@SessionAttributes("roles")
public class HomeController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	MessageSource messageSource;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
	
	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;

	
    @GetMapping("/")
    public String root(ModelMap model) {
        return userIsalive("index");
    }
    @GetMapping("/login")
    public String login(ModelMap model) {
        return "login";
    }
    @RequestMapping("/access-denied")
    public String accessDenied(ModelMap model) {
    	model.addAttribute("loggedinuser", getPrincipal());
        return userIsalive("/error/access-denied");
    }
    
    @GetMapping("/user")
    public String userIndex(ModelMap model) {
        return userIsalive("user/index");
    }
    @GetMapping("/admin")
    public String admin(ModelMap model) {
        return userIsalive("admin/admin");
    }
    @GetMapping("/student")
    public String student(ModelMap model) {
        return userIsalive("student/student");
    }
    @GetMapping("/staff")
    public String classteacher(ModelMap model) {
        return userIsalive("staff/staff");
    }
    @GetMapping("/hod")
    public String hod(ModelMap model) {
        return userIsalive("hod/hod");
    }
    @GetMapping("/parent")
    public String parent(ModelMap model) {
        return userIsalive("parent/parent");
    }
    
    @GetMapping("/logout")
	public String logoutPage (HttpServletRequest request, HttpServletResponse response){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?logout";
	}

	/**
	 * This method returns the principal[user-name] of logged-in user.
	 */
	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
	
	/**
	 * This method returns true if users is already authenticated [logged-in], else false.
	 */
	private boolean isCurrentAuthenticationAnonymous() {
	    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return authenticationTrustResolver.isAnonymous(authentication);
	}
	
	public String userIsalive (String page) {
		if (isCurrentAuthenticationAnonymous()) {
			page = "/login";
	    }
		return page;
	}

}
