package com.uniovi.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.Teacher;
import com.uniovi.entities.User;
import com.uniovi.services.TeachersService;

@Component
public class TeacherFormValidator implements Validator {

	@Autowired
	private TeachersService teachersService;

	@Override
	public boolean supports(Class<?> aClass) {
		return Teacher.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Teacher teacher = (Teacher) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");
		if (teacher.getDni().length() != 9) {
			errors.rejectValue("dni", "Error.teacher.dni.length");
		} else if (!Character.isLetter(teacher.getDni().charAt(8))) {
			errors.rejectValue("dni", "Error.teacher.dni.finalChar");
		}
		if (teachersService.getTeacherByDni(teacher.getDni()) != null) {
			errors.rejectValue("dni", "Error.teacher.dni.duplicate");
		}
		if (teacher.getName().length() < 5 || teacher.getName().length() > 24) {
			errors.rejectValue("name", "Error.teacher.name.length");
		}
		if (teacher.getSurname().length() < 5 || teacher.getSurname().length() > 24) {
			errors.rejectValue("surname", "Error.teacher.surname.length");
		}
	}
}
