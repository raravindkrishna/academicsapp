package com.example.academicsapp.service;

import com.example.academicsapp.models.Student;

import java.util.List;

public interface ClassGroupStudentService {
    List<Student> getAllStudentsInClassGroup(Integer classGroupId);
    String addStudentToClassGroup(Integer classGroupId, Integer studentId);

    String removeStudentsFromClassGroup(Integer classGroupId,List<Integer> ids);
}
