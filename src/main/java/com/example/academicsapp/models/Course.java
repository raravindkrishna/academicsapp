package com.example.academicsapp.models;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String courseName;
    private String description;

    @Column(unique = true)
    private String courseCode;
}
