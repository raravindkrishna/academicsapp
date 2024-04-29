package com.example.academicsapp.controller;

import com.example.academicsapp.models.ClassGroupStudent;
import com.example.academicsapp.models.Student;
import com.example.academicsapp.service.ServiceImpls.ClassGroupStudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import java.util.List;

@RestController
@RequestMapping("/api/classGroup/{classGroupId}/students")
public class ClassGroupStudentsController {

    @Autowired
    private ClassGroupStudentServiceImpl classGroupStudentService;

    @GetMapping   
    public ResponseEntity<List<Student>> getAllStudentsInClassGroup(@PathVariable Integer classGroupId) {
        try {
            List<Student> students = classGroupStudentService.getAllStudentsInClassGroup(classGroupId);
            return new ResponseEntity<>(students, HttpStatus.OK);
        }
        catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching students", e);
        }
    }

    @PostMapping("/{studentId}")
    public ResponseEntity<ClassGroupStudent> addStudentToClassGroup(@PathVariable Integer classGroupId, @PathVariable Integer studentId){
       try {
           ClassGroupStudent classGroupStudent = classGroupStudentService.addStudentToClassGroup(classGroupId, studentId);
           return new ResponseEntity<>(classGroupStudent, HttpStatus.CREATED);
       }
       catch (IllegalArgumentException e) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
       catch (RuntimeException e){
           String jsonString = "{\"key\": \"value\"}"; 
           Gson gson = new Gson();
           ClassGroupStudent yourObject = gson.fromJson(jsonString, ClassGroupStudent.class);

           return new ResponseEntity<>(yourObject,HttpStatus.OK);
       }
       catch (Exception e) {
           throw new RuntimeException("Error occurred while creating Student", e);
       }
    }

    @DeleteMapping
    public ResponseEntity removeStudentsFromClassGroup(@PathVariable Integer classGroupId,@RequestParam("id") List<Integer> ids){
        try {
            classGroupStudentService.removeStudentsFromClassGroup(classGroupId, ids);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while removing student(s)", e);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
