package com.uca.capas.tarea5.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.tarea5.dao.EstudianteDAO;
import com.uca.capas.tarea5.domain.Estudiante;

@Controller
public class MainController {
	@Autowired
	private EstudianteDAO dao;
	
	@RequestMapping("/inicio")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		Estudiante stu = new Estudiante();
		mav.addObject("student", stu);
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping("/listado")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		List<Estudiante> studentsList = null;
		try {
			studentsList = dao.showAll();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		//Añadiendo lista a tabala Thymeleaf
		mav.addObject("students", studentsList);
		mav.setViewName("list");
		return mav;
	}
	
	
	@RequestMapping("/formEstudiante")
	public ModelAndView formEstudiante(@Valid @ModelAttribute Estudiante student, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if(!result.hasErrors()) {
			try {
				dao.insertEs(student);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			student = new Estudiante();
			mav.addObject("student", student);
			
		}
		mav.setViewName("index");
		
		return mav;
	}
}