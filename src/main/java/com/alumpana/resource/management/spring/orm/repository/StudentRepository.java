package com.alumpana.resource.management.spring.orm.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.alumpana.resource.management.spring.orm.entity.Student;
import com.alumpana.resource.management.spring.orm.entity.User;

@Repository
public class StudentRepository extends AbstractCrudRepository<Integer, Student> {

	static final Logger logger = LoggerFactory.getLogger(StudentRepository.class);

	public Student findById(int id) {
		Student user = getByKey(id);
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<Student> findAllUsers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Student> users = (List<Student>) criteria.list();
		return users;
	}

	public void save(Student user) {
		persist(user);
	}

	public Student findByEmail(String email) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("email", email));
		List<Student>user = (List<Student>)crit.list();
		if (user !=null && user.size() >0) {
			return user.get(0);
		} 
		return null;
	}


}
