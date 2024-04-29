package com.example.academicsapp.service;

import com.example.academicsapp.models.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(Integer id);
    Student createStudent(Student classGroup);
    Student updateStudentById(Integer id, Student newStudent);
    void deleteStudentById(Integer id);
}
