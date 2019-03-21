package com.coon.dynamic.service;

import com.coon.dynamic.common.datasource.MyDataSource;
import com.coon.dynamic.common.enums.DatabaseTypeEnum;
import com.coon.dynamic.dao.StudentDAO;
import com.coon.dynamic.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Chen
 */
@Service
public class StudentService {
	@Autowired
	private StudentDAO studentDAO;

	@MyDataSource(DatabaseTypeEnum.SUMMER_DATABASE_1)
	public List<Student> list() {
		return studentDAO.list();
	}

	@MyDataSource(DatabaseTypeEnum.SUMMER_DATABASE_2)
	public List<Student> findAll() {
		return studentDAO.list();
	}

	@MyDataSource(DatabaseTypeEnum.SUMMER_DATABASE_3)
	public List<Student> findPage() {
		return studentDAO.list();
	}
}
