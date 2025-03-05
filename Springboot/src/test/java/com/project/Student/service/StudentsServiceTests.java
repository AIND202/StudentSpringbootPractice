package com.project.Student.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.project.Student.exception.StudentAlreadyExistsException;
import com.project.Student.exception.StudentNotFoundException;
import com.project.Student.model.Students;
import com.project.Student.repository.StudentsRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentsServiceTests {

    @InjectMocks
    private StudentsService service;

    @Mock
    private StudentsRepository repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddNewStudent() throws StudentAlreadyExistsException {
        Students student = new Students(1, "John Doe", 20, "john.doe@example.com", "123 Main St");
        when(repository.findById(student.getStudentId())).thenReturn(Optional.empty());

        Students result = service.newStudents(student);
        assertEquals(student, result);
        verify(repository, times(1)).save(student);
    }

    @Test
    public void testAddNewStudent_AlreadyExists() {
        Students student = new Students(1, "John Doe", 20, "john.doe@example.com", "123 Main St");
        when(repository.findById(student.getStudentId())).thenReturn(Optional.of(student));

        assertThrows(StudentAlreadyExistsException.class, () -> {
            service.newStudents(student);
        });
    }

    @Test
    public void testDeleteStudent() throws StudentNotFoundException {
        int studentId = 1;
        Students student = new Students(1, "John Doe", 20, "john.doe@example.com", "123 Main St");
        when(repository.findById(studentId)).thenReturn(Optional.of(student));

        String result = service.deleteStudent(studentId);
        assertEquals("Student deleted successfully", result);
        verify(repository, times(1)).deleteById(studentId);
    }

    @Test
    public void testDeleteStudent_NotFound() {
        int studentId = 1;
        when(repository.findById(studentId)).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> {
            service.deleteStudent(studentId);
        });
    }

    @Test
    public void testGetStudentById() throws StudentNotFoundException {
        int studentId = 1;
        Students student = new Students(1, "John Doe", 20, "john.doe@example.com", "123 Main St");
        when(repository.findById(studentId)).thenReturn(Optional.of(student));

        Students result = service.getStudentById(studentId);
        assertEquals(student, result);
    }

    @Test
    public void testGetStudentById_NotFound() {
        int studentId = 1;
        when(repository.findById(studentId)).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> {
            service.getStudentById(studentId);
        });
    }

    @Test
    public void testUpdateStudent() throws StudentNotFoundException {
        int studentId = 1;
        Students student = new Students(1, "John Doe", 20, "john.doe@example.com", "123 Main St");
        when(repository.findById(studentId)).thenReturn(Optional.of(student));

        Students updatedStudent = new Students(1, "Jane Doe", 21, "jane.doe@example.com", "456 Main St");
        when(repository.save(updatedStudent)).thenReturn(updatedStudent);

        Students result = service.updateStudent(updatedStudent, studentId);
        assertEquals(updatedStudent, result);
    }

    @Test
    public void testUpdateStudent_NotFound() {
        int studentId = 1;
        Students student = new Students(1, "John Doe", 20, "john.doe@example.com", "123 Main St");
        when(repository.findById(studentId)).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> {
            service.updateStudent(student, studentId);
        });
    }

    @Test
    public void testGetAllStudents() throws StudentNotFoundException {
        List<Students> studentsList = new ArrayList<>();
        studentsList.add(new Students(1, "John Doe", 20, "john.doe@example.com", "123 Main St"));
        when(repository.findAll()).thenReturn(studentsList);

        List<Students> result = service.getAllStudents();
        assertEquals(studentsList, result);
    }
}
