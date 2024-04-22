package com.example.academicsapp.service;

import com.example.academicsapp.dao.CourseDao;
import com.example.academicsapp.dao.FacultyDao;
import com.example.academicsapp.models.Course;
import com.example.academicsapp.models.Faculty;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private FacultyDao facultyDao;

    public List<Course> getAllCourses(){
        return courseDao.findAll();
    }

    public Course getCourseById(Integer id){
        return courseDao.findById(id).orElse(null);
    }
    public Course createCourse(Course course){
        return courseDao.save(course);
    }

    public Course updateCourse(Integer id, Course newCourse) {
        Course oldCourse = courseDao.findById(id).orElse(null);

        if(oldCourse==null){
            return null;
        }
        BeanUtils.copyProperties(newCourse, oldCourse, "id");
        return courseDao.save(oldCourse);
    }
    public void deleteCourseById(Integer id){
        courseDao.deleteById(id);
    }

    public List<Faculty> getAllFacultiesOfThisCourse(Integer courseId){
        return facultyDao.getAllFacultiesOfThisCourse(courseId);
    }
}
