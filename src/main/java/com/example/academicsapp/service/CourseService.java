package com.example.academicsapp.service;

import com.example.academicsapp.models.Course;
import com.example.academicsapp.models.Faculty;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();
    Course getCourseById(Integer id);
    Course createCourse(Course classGroup);
    Course updateCourseById(Integer id, Course newCourse);
    void deleteCourseById(Integer id);
    List<Faculty> getAllFacultiesOfThisCourse(Integer courseId);
}
