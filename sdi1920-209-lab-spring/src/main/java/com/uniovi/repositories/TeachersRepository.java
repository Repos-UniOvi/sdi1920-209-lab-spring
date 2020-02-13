package com.uniovi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Teacher;

public interface TeachersRepository extends CrudRepository<Teacher, Long>{

}
