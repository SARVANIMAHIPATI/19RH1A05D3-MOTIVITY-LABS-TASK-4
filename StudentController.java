package com.codebun.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codebun.model.Student;
import com.codebun.service.StudentService;

@Controller
public class StudentController  {
	@Autowired
	private StudentService service;

	@GetMapping("/")
	public ModelAndView home(Model m) {
		Student student = new Student();
		m.addAttribute("student", student);
		ModelAndView modelAndView = new ModelAndView("register");
		return modelAndView;
	}

	@PostMapping("/register")
	public String register(@ModelAttribute("student") Student student, Model model) {
		service.registerStudent(student);
		model.addAttribute("success", "Registered Successfully");
		return "register";
	}

	@GetMapping("/login")
	public String loginDisplay(Model m, HttpSession session) {

		Student student = new Student();

		if (session.getAttribute("student") != null) {
			session.invalidate();
			System.out.println("here");
			m.addAttribute("success", "You have logout Successfully!!!");
		}
		m.addAttribute("student", student);
		return "login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("student") Student student, Model model, HttpSession session) {
		Student student2 = service.loginStudent(student);
		System.out.println("student2" + student2);
		if (student2 != null) {
			System.out.println("hello");
			model.addAttribute("student", student2);
			session.setAttribute("student", student2);
			return "welcome";
		}
		if (student2 == null) {
			System.out.println("on");
			model.addAttribute("error", "Invalid Credentials");
		}
		return "login";

	}

	
}