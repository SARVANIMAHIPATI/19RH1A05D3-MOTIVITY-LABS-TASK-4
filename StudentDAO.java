package com.codebun.dao;

import com.codebun.model.Student;

public interface StudentDAO {

	public void registerStudent(Student student);

	public Student loginStudent(Student student);
}