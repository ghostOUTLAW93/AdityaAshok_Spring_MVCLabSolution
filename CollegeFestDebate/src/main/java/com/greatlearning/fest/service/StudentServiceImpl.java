package com.greatlearning.fest.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.greatlearning.fest.entity.Student;

//Implementation for service interface


@Repository
public class StudentServiceImpl implements StudentService {

	private SessionFactory sessionFactory;

	// create session
	private Session session;

	@Autowired
	StudentServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}

	}

	//provide the list of all students
	@Override
	public List<Student> getAllStudents() {
		Transaction tx=session.beginTransaction();
		List<Student> students=session.createQuery("from Student").list();
		tx.commit();
		return students;
	}

	//find the student with the given id
	@Override
	public Student findById(int id) {
		Transaction tx=session.beginTransaction();
		Student student=session.get(Student.class,id);
		tx.commit();
		return student;
	}
	//save student data
	@Override
	public void save(Student student) {
		Transaction tx=session.beginTransaction();
		session.save(student);
		tx.commit();

	}
	//delete student data
	@Override
	public void delete(Student student) {
		Transaction tx=session.beginTransaction();
		session.delete(student);
		tx.commit();

	}

}
