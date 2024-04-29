package com.example.academicsapp.controller;

import com.example.academicsapp.models.Course;
import com.example.academicsapp.models.Faculty;
import com.example.academicsapp.service.ServiceImpls.CourseServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseServiceImpl courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        try {
            List<Course> courses = courseService.getAllCourses();
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching courses", e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Integer id) {
        try {
            Course course = courseService.getCourseById(id);
            return new ResponseEntity<>(course, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching course with id: " + id, e);
        }
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        try {
            Course addedCourse = courseService.createCourse(course);
            return new ResponseEntity<>(addedCourse, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while creating course", e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourseById(@PathVariable Integer id, @RequestBody Course newCourse) {
        try {
            Course updatedCourse = courseService.updateCourseById(id, newCourse);
            return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while updating course with id: " + id, e);
        }
    }

    @DeleteMapping
    public ResponseEntity deleteCourseById(@RequestParam("id") List<Integer> ids) {
        try {
            for (Integer id : ids) {
                courseService.deleteCourseById(id);
            }
            return new ResponseEntity(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while deleting course(s)", e);
        }
    }


    @GetMapping("/{courseId}/faculties")
    public ResponseEntity<List<Faculty>> getAllFacultiesOfThisCourse(@PathVariable Integer courseId) {
        try {
           List<Faculty> faculties =  courseService.getAllFacultiesOfThisCourse(courseId);
            return new ResponseEntity<>(faculties, HttpStatus.OK);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            throw new RuntimeException("Error occurred while updating course with id: " + courseId, e);
        }
    }
}