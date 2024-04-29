package com.example.academicsapp.service.ServiceImpls;

import com.example.academicsapp.dao.CourseDao;
import com.example.academicsapp.dao.FacultyDao;
import com.example.academicsapp.models.Course;
import com.example.academicsapp.models.Faculty;
import com.example.academicsapp.service.CourseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private FacultyDao facultyDao;

    @Override
    public List<Course> getAllCourses(){
        return courseDao.findAll();
    }

    @Override
    public Course getCourseById(Integer id){
        if (id == null) {
            throw new IllegalArgumentException("Course id must not be null");
        }
        return courseDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + id));
    }

    @Override
    public Course createCourse(Course course){
        if (course == null || course.getCourseName()==null||course.getCourseCode()==null) {
            throw new IllegalArgumentException("Course object must not be null");
        }
        return courseDao.save(course);
    }

    @Override
    public Course updateCourseById(Integer id, Course newCourse) {
        if (id == null || newCourse.getCourseCode()==null || newCourse.getCourseName()==null) {
            throw new IllegalArgumentException("Course field values like id, code, name must not be null");
        }
        Course oldCourse = courseDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + id));

        BeanUtils.copyProperties(newCourse, oldCourse, "id");
        return courseDao.save(oldCourse);
    }

    @Override
    public void deleteCourseById(Integer id){
        if (id == null) {
            throw new IllegalArgumentException("Course id must not be null");
        }
        courseDao.deleteById(id);
    }

    @Override
    public List<Faculty> getAllFacultiesOfThisCourse(Integer courseId){
        if (courseId == null) {
            throw new IllegalArgumentException("Course id must not be null");
        }
        return facultyDao.getAllFacultiesOfThisCourse(courseId);
    }
}
