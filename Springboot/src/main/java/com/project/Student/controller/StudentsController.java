package com.project.Student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Student.exception.StudentAlreadyExistsException;
import com.project.Student.exception.StudentNotFoundException;
import com.project.Student.model.Students;
import com.project.Student.service.StudentsService;

@RestController
@CrossOrigin
@RequestMapping("/students")
public class StudentsController {

    
    private final StudentsService studentsService;

    @Autowired
    public StudentsController(StudentsService studentsService){
        this.studentsService = studentsService;
    }

    @PostMapping("/addStudent")
    public ResponseEntity<Students> addStudent(@RequestBody Students student) throws StudentAlreadyExistsException{
        Students newStudents = this.studentsService.newStudents(student);
        return ResponseEntity.status(201).body(newStudents);
    }

    @DeleteMapping("/deleteStudent/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable int studentId) throws StudentNotFoundException{
            String getStudent = this.studentsService.deleteStudent(studentId);{
            return ResponseEntity.status(200).body(getStudent);
        }
    }

    @GetMapping("/fetchStudent/{studentId}")
    public ResponseEntity<Students> getStudentById(@PathVariable int studentId) throws StudentNotFoundException{
        Students getStudent = this.studentsService.getStudentById(studentId);
        return ResponseEntity.status(200).body(getStudent);
    }

    @GetMapping("/studentList")
    public ResponseEntity<List<Students>> getAllStudents() throws StudentNotFoundException{
        List<Students> studentList = this.studentsService.getAllStudents();
        return ResponseEntity.status(200).body(studentList);
    }

    @PutMapping("/updateStudent/{studentId}")
    public ResponseEntity<Students> updateStudentById(@RequestBody Students student, @PathVariable int studentId) throws StudentNotFoundException{
        Students getStudent = this.studentsService.updateStudent(student, studentId);
        return ResponseEntity.status(200).body(getStudent);
    }

}
