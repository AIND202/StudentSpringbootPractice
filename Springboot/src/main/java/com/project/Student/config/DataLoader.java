package com.project.Student.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.Student.model.Students;
import com.project.Student.repository.StudentsRepository;



@Component
public class DataLoader {

    private final StudentsRepository studentRepo;

    @Autowired
    public DataLoader(StudentsRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    @jakarta.annotation.PostConstruct
    private void loadUsers() {
        studentRepo.save(new Students(1,"John Doe", 20,"john.doe@example.com", "123 Main St, NY"));
        studentRepo.save(new Students(2,"Jane Smith", 22,"jane.smith@example.com", "456 Elm St, LA"));
        studentRepo.save(new Students(3,"Michael Brown",21, "michael.brown@example.com", "789 Oak St, TX"));
        studentRepo.save(new Students(4,"Emily Davis", 19,"emily.davis@example.com", "101 Pine St, FL"));
        studentRepo.save(new Students(5,"Sarah Wilson",23, "sarah.wilson@example.com", "202 Maple St, CA"));
    }
}

