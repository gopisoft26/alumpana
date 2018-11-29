package com.alumpana.resource.management.spring.orm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alumpana.resource.management.spring.orm.entity.Student;
import com.alumpana.resource.management.spring.orm.repository.StudentRepository;
import com.alumpana.resource.management.spring.orm.service.StudentService;

@Service("studentService")
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository dao;

	public Student findById(int id) {
		return dao.findById(id);
	}
	public void saveUser(Student user) {
		dao.save(user);
	}
	public List<Student> findAllUsers() {
		return dao.findAllUsers();
	}
	@Override
	public Student findByEmail(String email) {
		return dao.findByEmail(email);
	}
}
