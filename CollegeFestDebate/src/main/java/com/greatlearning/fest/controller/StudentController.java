package com.greatlearning.fest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.fest.entity.Student;
import com.greatlearning.fest.service.StudentService;

//here /students is the common relative mapping for all URI
@Controller
@RequestMapping("/students")
public class StudentController {
	@Autowired
	StudentService studentService;
	
	//Mapping for /students/list
	@RequestMapping("/list")
	public String StudentList(Model model)
	{
		List<Student> students=studentService.getAllStudents();
		model.addAttribute("students", students);
		return "list-students";
	}
	
	//Mapping for /students/showFormForAdd
	@RequestMapping("/showFormForAdd")
	public String addStudent(Model model)
	{
		Student student=new Student();
		model.addAttribute("student",student);
		return "student-form";
	}
	
	//Mapping for /students/save
	@RequestMapping("/save")
	public String saveStudent(@RequestParam("id") int id,
			@RequestParam("name") String name,
			@RequestParam("department") String department,
			@RequestParam("country") String country)
	{
		System.out.println(id);
		Student student;
		if (id != 0) {
			student = studentService.findById(id);
			student.setName(name);
			student.setDepartment(department);
			student.setCountry(country);
		} else
			student = new Student(name, department, country);
		// save the Student
		studentService.save(student);

		// using a redirect to prevent duplicate submissions
		return "redirect:/students/list";
	}
	
	//Mapping for /students/update
	@RequestMapping("/update")
	public String updateStudent(@RequestParam("studentId") int id,Model model)
	{
		Student student = studentService.findById(id);
		model.addAttribute("student", student);
		return "student-form";
	}
	
	//Mapping for /students/delete
	@RequestMapping("/delete")
	public String deleteStudent(@RequestParam("studentId") int id,Model model)
	{
		Student student = studentService.findById(id);
		studentService.delete(student);
		return "redirect:/students/list";
	}

}
