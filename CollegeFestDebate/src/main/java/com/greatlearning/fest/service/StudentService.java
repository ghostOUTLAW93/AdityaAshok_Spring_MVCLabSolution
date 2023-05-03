package com.greatlearning.fest.service;

import java.util.List;

import com.greatlearning.fest.entity.Student;

//service layer interface

public interface StudentService {
	
	public List<Student> getAllStudents();
	public Student findById(int id);
	public void save(Student student);
	public void delete(Student student);
	
}
