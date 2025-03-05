package com.project.Student.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.Student.exception.StudentAlreadyExistsException;
import com.project.Student.exception.StudentNotFoundException;
import com.project.Student.model.Students;

@Service
public interface StudentsService {

    Students newStudents(Students student) throws StudentAlreadyExistsException;

    String deleteStudent(int studentId) throws StudentNotFoundException;

    Students getStudentById(int studentId) throws StudentNotFoundException;

    Students updateStudent(Students student, int studentId) throws StudentNotFoundException;

    List<Students> getAllStudents() throws StudentNotFoundException;

}
