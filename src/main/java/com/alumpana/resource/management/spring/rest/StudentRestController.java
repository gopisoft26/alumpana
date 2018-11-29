package com.alumpana.resource.management.spring.rest;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.alumpana.resource.management.spring.orm.entity.Student;
import com.alumpana.resource.management.spring.orm.service.StudentService;

/**
 * @author Sivakumar ARUMUGAM
 * 
 */


@RequestMapping("/rest/student")
@RestController
//@SessionAttributes("roles")
public class StudentRestController {

	@Autowired
	StudentService service;
	
	@RequestMapping(value="/save",method = RequestMethod.GET)
	public void save() {
		Student student = service.findByEmail("gopisoft");
		if (student == null) {
			student = new Student();
			student.setEmail("gopisoft2@gmail.com");
			student.setCreadedBy("Gopi");
			student.setCreatedOn(new Date());
		} else {
			student.setModifiedBy("Gopi");
			student.setModifiedOn(new Date());
		}

		student.setFirstName("Gopi");
		student.setLastName("A");
		service.saveUser(student);
	}
	
	@GetMapping("/list")
	public List<Student> list() {
		return service.findAllUsers();
	}

}