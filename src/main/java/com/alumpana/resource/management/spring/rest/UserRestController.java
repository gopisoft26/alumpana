package com.alumpana.resource.management.spring.rest;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.alumpana.resource.management.spring.orm.entity.User;
import com.alumpana.resource.management.spring.orm.entity.UserProfile;
import com.alumpana.resource.management.spring.orm.service.UserService;

/**
 * @author Sivakumar ARUMUGAM
 * 
 */

@RestController
@RequestMapping("/rest/user")
//@SessionAttributes("roles")
public class UserRestController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/save",method = RequestMethod.GET)
	public void save() {
		User user = userService.findBySSO("gopisoft");
		Set<UserProfile> userProfiles = new HashSet<UserProfile>();
		
		UserProfile pro = new UserProfile();
		pro.setType("DB");
		userProfiles.add(pro);
		
		if (user == null) {
			user = new User();
			user.setCreadedBy("Gopi");
			user.setCreatedOn(new Date());
			user.setSsoId("gopi@gmail.com");
		} else {
			user.setModifiedBy("Gopi");
			user.setModifiedOn(new Date());
		}
		user.setUserProfiles(userProfiles);
		user.setEmail("gopi@gmail.com");
		user.setFirstName("Gopi");
		user.setLastName("A");
		user.setPassword("gopi");
		userService.saveUser(user);
	}
	
	@GetMapping("/list")
	public List<User> list() {
		return userService.findAllUsers();
	}


	
	
	
}