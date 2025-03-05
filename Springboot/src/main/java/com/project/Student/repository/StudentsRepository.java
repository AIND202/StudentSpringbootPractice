package com.project.Student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Student.model.Students;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Integer>{

}
