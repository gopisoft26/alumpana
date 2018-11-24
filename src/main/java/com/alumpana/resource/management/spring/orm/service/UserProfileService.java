package com.alumpana.resource.management.spring.orm.service;

import java.util.List;

import com.alumpana.resource.management.spring.orm.entity.UserProfile;

public interface UserProfileService {

	UserProfile findById(int id);

	UserProfile findByType(String type);
	
	List<UserProfile> findAll();
	
}
