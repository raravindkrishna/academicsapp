package com.example.academicsapp.controller;

import com.example.academicsapp.models.Student;
import com.example.academicsapp.service.ServiceImpls.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Integer id) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            String errorMessage = "Student with id: " + id + "is not found";
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student addedStudent = studentService.createStudent(student);
        return new ResponseEntity<>(addedStudent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudentById(@PathVariable Integer id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudentById(id, student);
        if (updatedStudent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteStudentById(@RequestParam("id") List<Integer> ids) {
        for (Integer id : ids) {
            studentService.deleteStudentById(id);
        }
        return new ResponseEntity( HttpStatus.OK);
    }
}
