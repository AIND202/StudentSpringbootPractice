package com.project.Student.model;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Students {

    @Id
    private int studentId;
    private String studentName;
    private int studentAge;
    private String studentEmail;
    private String studentAddress;

    public Students() {
    }

    public Students(int studentId, String studentName, int studentAge, String studentEmail, String studentAddress) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentEmail = studentEmail;
        this.studentAddress = studentAddress;
    }

}
