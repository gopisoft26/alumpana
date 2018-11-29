package com.alumpana.resource.management.spring.orm.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.alumpana.resource.management.spring.orm.entity.Student;

@Service
public interface StudentService {
	
	Student findById(int id);
	Student findByEmail(String sso);
	void saveUser(Student user);
	List<Student> findAllUsers();

}