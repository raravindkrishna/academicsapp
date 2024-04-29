package com.example.academicsapp.service;

import com.example.academicsapp.models.ClassGroupStudent;
import com.example.academicsapp.models.Student;

import java.util.List;

public interface ClassGroupStudentService {
    List<Student> getAllStudentsInClassGroup(Integer classGroupId);
    ClassGroupStudent addStudentToClassGroup(Integer classGroupId, Integer studentId);

    void removeStudentsFromClassGroup(Integer classGroupId,List<Integer> ids);
}
