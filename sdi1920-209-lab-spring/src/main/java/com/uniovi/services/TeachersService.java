package com.uniovi.services;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.uniovi.entities.Teacher;

@Service
public class TeachersService {
	private List<Teacher> teachersList = new LinkedList<Teacher>();

	@PostConstruct
	public void init() {
		teachersList.add(new Teacher(1L,"DniDeEnrique", "Enrique", "De la Cal","Lecturer"));
		teachersList.add(new Teacher(2L, "DniDeEdward", "Edward", "Rolando", "Laboratory Teacher"));
	}

	public List<Teacher> getTeachers() {
		return teachersList;
	}

	public Teacher getTeacher(Long id) {
		return teachersList.stream().filter(Teacher -> Teacher.getId().equals(id)).findFirst().get();
	}

	public void addTeacher(Teacher teacher) {
		// Si en Id es null le asignamos el ultimo + 1 de la lista
		if (teacher.getId() == null) {
			teacher.setId(teachersList.get(teachersList.size() - 1).getId() + 1);
		}
		teachersList.add(teacher);
	}

	public void deleteTeacher(Long id) {
		teachersList.removeIf(teacher -> teacher.getId().equals(id));
	}
}
