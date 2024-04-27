package com.example.academicsapp.controller;

import com.example.academicsapp.models.Course;
import com.example.academicsapp.models.Faculty;
import com.example.academicsapp.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Integer id){
        Course course = courseService.getCourseById(id);
        if(course==null){
            String errorMessage = "Course with id: "+ id + " is not found";
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course){

        Course addedCourse = courseService.createCourse(course);
        return new ResponseEntity<>(addedCourse, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourseById(@PathVariable Integer id, @RequestBody Course newCourse){
        Course updatedCourse = courseService.updateCourse(id, newCourse);
        if(updatedCourse==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteCourseById(@RequestParam("id") List<Integer> ids){
        for(Integer id : ids) {
            courseService.deleteCourseById(id);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{courseId}/faculties")
    public List<Faculty> getAllFacultiesOfThisCourse(@PathVariable Integer courseId){
        return courseService.getAllFacultiesOfThisCourse(courseId);
    }
}
