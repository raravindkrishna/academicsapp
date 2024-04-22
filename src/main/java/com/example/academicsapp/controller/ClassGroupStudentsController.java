package com.example.academicsapp.controller;

import com.example.academicsapp.models.ClassGroup;
import com.example.academicsapp.models.ClassGroupStudent;
import com.example.academicsapp.models.Student;
import com.example.academicsapp.service.ClassGroupService;
import com.example.academicsapp.service.ClassGroupStudentService;
import com.example.academicsapp.service.ClassGroupStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home/classGroup/{classGroupId}/students")
public class ClassGroupStudentsController {

    @Autowired
    private ClassGroupStudentService classGroupStudentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudentsInClassGroup(@PathVariable Integer classGroupId) {
            List<Student> students = classGroupStudentService.getAllStudentsInClassGroup(classGroupId);
            return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping("/{studentId}")
    public ResponseEntity<String> addStudentToClassGroup(@PathVariable Integer classGroupId, @PathVariable Integer studentId){
        String message = classGroupStudentService.addStudentToClassGroup(classGroupId, studentId);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> removeStudentsFromClassGroup(@PathVariable Integer classGroupId,@RequestParam("id") List<Integer> ids){
        String message = classGroupStudentService.removeStudentsFromClassGroup( classGroupId ,ids);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
}
