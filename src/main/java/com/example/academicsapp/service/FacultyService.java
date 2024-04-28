package com.example.academicsapp.service;

import com.example.academicsapp.models.Course;
import com.example.academicsapp.models.Faculty;

import java.util.List;

public interface FacultyService {
    List<Faculty> getAllFaculties();
    Faculty getFacultyById(Integer id);
    Faculty createFaculty(Faculty classGroup);
    Faculty updateFacultyById(Integer id, Faculty newFaculty);
    void deleteFacultyById(Integer id);
    List<Course> getAllCoursesOfThisFaculty(Integer facultyId);
}
