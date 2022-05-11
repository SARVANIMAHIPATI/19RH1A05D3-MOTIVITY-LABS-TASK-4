package com.codebun.service;

import org.springframework.stereotype.Service;

import com.codebun.model.Student;
@Service
public interface StudentService {

	public void registerStudent(Student student);

	public Student loginStudent(Student student);
}