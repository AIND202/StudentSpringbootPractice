package com.project.Student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Student.exception.StudentAlreadyExistsException;
import com.project.Student.exception.StudentNotFoundException;
import com.project.Student.model.Students;
import com.project.Student.repository.StudentsRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentsServiceImpl implements StudentsService{

    private final StudentsRepository studentRepo;

    @Autowired
    public StudentsServiceImpl(StudentsRepository studentRepo){
        this.studentRepo = studentRepo;
    }

    @Override
    public Students newStudents(Students student) throws StudentAlreadyExistsException {
        Students getStudent = studentRepo.findById(student.getStudentId()).orElse(null);
        if(getStudent != null){
            throw new StudentAlreadyExistsException("Student already exists cannot add new Student Details");  
        }
        else{
            return studentRepo.save(student);
        }
    }


    @Override
    @Transactional
    public String deleteStudent(int studentId) throws StudentNotFoundException {
        Students getStudent = studentRepo.findById(studentId).orElse(null);
        if(getStudent != null){
            studentRepo.deleteById(studentId);
            return ("Student deleted successfully");
        }
        else{
            throw new StudentNotFoundException("Cannot find student with ID: " + studentId + ", cannot delete");
        }
        
    }

    @Override
    public Students getStudentById(int studentId) throws StudentNotFoundException {
        Students getStudent = studentRepo.findById(studentId).orElse(null);
        if(getStudent != null){
            return getStudent;
        }
        else{
            throw new StudentNotFoundException("Cannot find student with ID: " + studentId);
        }
    }

    @Override
    public Students updateStudent(Students student, int studentId) throws StudentNotFoundException {
        Students getStudent = studentRepo.findById(studentId).orElse(null);
        if(getStudent != null){
            student.setStudentId(studentId);
            studentRepo.save(student);
            return student;
        }
        else{
            throw new StudentNotFoundException("Student with ID " + studentId + ", not found. Cannot Update");
        }
    }

    @Override
    public List<Students> getAllStudents() throws StudentNotFoundException {
        List<Students> studentList = this.studentRepo.findAll();
        if(studentList.size() != 0){
            return studentList;
        }
        else{
            throw new StudentNotFoundException("Student not found");
        }
    }

}
