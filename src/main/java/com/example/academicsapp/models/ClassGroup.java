package com.example.academicsapp.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ClassGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String classGroupName;

    @ManyToOne
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    private Faculty faculty;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;
}
