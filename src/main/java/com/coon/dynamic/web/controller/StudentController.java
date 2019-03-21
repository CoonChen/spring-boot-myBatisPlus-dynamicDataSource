package com.coon.dynamic.web.controller;

import com.coon.dynamic.domain.Student;
import com.coon.dynamic.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Chen
 */
@RestController
@RequestMapping(value = "student")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "list")
	public List<Student> list() {
		return studentService.list();
	}

	@RequestMapping(value = "findAll")
	public List<Student> findAll() {
		return studentService.findAll();
	}

	@RequestMapping(value = "findPage")
	public List<Student> findPage() {
		return studentService.findPage();
	}
}
