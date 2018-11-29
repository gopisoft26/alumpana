package com.alumpana.resource.management.spring.orm.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="Student")
public class Student extends Tracker implements Serializable{

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
		
	@NotEmpty
	@Column(name="FIRST_NAME", nullable=false)
	private String firstName;

	@NotEmpty
	@Column(name="LAST_NAME", nullable=false)
	private String lastName;

	@NotEmpty
	@Column(name="EMAIL", nullable=false)
	private String email;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
	
}
