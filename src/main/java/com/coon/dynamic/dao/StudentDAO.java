package com.coon.dynamic.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coon.dynamic.domain.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Chen
 */
@Mapper
public interface StudentDAO extends BaseMapper<Student> {
	List<Student> list();

}
