package com.alumpana.resource.management.spring.orm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alumpana.resource.management.spring.orm.entity.User;

@Service
public interface UserService {
	
	User findById(int id);
	
	User findBySSO(String sso);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserBySSO(String sso);

	List<User> findAllUsers(); 
	
	boolean isUserSSOUnique(Integer id, String sso);

}