package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Teacher;
import com.uniovi.services.TeachersService;
import com.uniovi.validators.TeacherFormValidator;

@Controller
public class TeachersController {
	@Autowired
	private TeachersService teachersService;

	@Autowired
	private TeacherFormValidator teacherFormValidator;

	@RequestMapping("/teacher/list")
	public String getList(Model model) {
		model.addAttribute("teacherList", teachersService.getTeachers());
		return "teacher/list";
	}

	@RequestMapping(value = "/teacher/add")
	public String getTeacher(Model model) {
		model.addAttribute("teacher", new Teacher());
		return "teacher/add";
	}

	@RequestMapping(value = "/teacher/add", method = RequestMethod.POST)
	public String setTeacher(@Validated Teacher teacher, BindingResult result) {
		teacherFormValidator.validate(teacher, result);
		if (result.hasErrors()) {
			return "teacher/add";
		} else {
			teachersService.addTeacher(teacher);
			return "redirect:/teacher/list";
		}
	}

	@RequestMapping("/teacher/details/{id}")
	public String getDetail(Model model, @PathVariable Long id) {
		model.addAttribute("teacher", teachersService.getTeacher(id));
		return "teacher/details";
	}

	@RequestMapping("/teacher/delete/{id}")
	public String deleteTeacher(@PathVariable Long id) {
		teachersService.deleteTeacher(id);
		return "redirect:/teacher/list";
	}

	@RequestMapping(value = "/teacher/edit/{id}")
	public String getEdit(Model model, @PathVariable Long id) {
		model.addAttribute("teacher", teachersService.getTeacher(id));
		return "teacher/edit";
	}

	@RequestMapping(value = "/teacher/edit/{id}", method = RequestMethod.POST)
	public String setEdit(Model model, @PathVariable Long id, @Validated Teacher teacher, BindingResult result) {
		teacher.setId(id);
		teacherFormValidator.validate(teacher, result);
		if (result.hasErrors())
			return "teacher/edit";
		else {
			Teacher original = teachersService.getTeacher(id);
			// modificar solo score y description
			original.setDni(teacher.getDni());
			original.setName(teacher.getName());
			original.setSurname(teacher.getSurname());
			original.setCategory(teacher.getCategory());
			teachersService.addTeacher(original);
			return "redirect:/mark/details/" + id;
		}
	}
}
