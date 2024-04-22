package com.example.academicsapp.controller;

import com.example.academicsapp.models.Course;
import com.example.academicsapp.models.Faculty;
import com.example.academicsapp.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home/faculty")
public class FacultyController {

    @Autowired
    FacultyService facultyService;
    @GetMapping
    public List<Faculty> getAllFaculties(){
        return facultyService.getAllFaculties();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFacultyById(@PathVariable Integer id){
        Faculty faculty = facultyService.getFacultyById(id);
        if(faculty==null){
            String errorMessage = "Faculty with id: " + id +"is not found";
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(faculty, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty){
        Faculty addedFaculty = facultyService.createFaculty(faculty);
        return new ResponseEntity<>(addedFaculty, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faculty> updateFacultyById(@PathVariable Integer id, @RequestBody Faculty faculty){
        Faculty updatedFaculty = facultyService.updateFacultyById(id, faculty);
        if(updatedFaculty==null){
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedFaculty, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteFacultyById(@RequestParam("id") List<Integer> ids){
        for(Integer id: ids){
            facultyService.deleteFacultyById(id);
        }
        String message = "Deleted Successfully";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/{facultyId}/courses")
    public List<Course> getAllFacultiesOfThisCourse(@PathVariable Integer facultyId){
        return facultyService.getAllCoursesOfThisFaculty(facultyId);
    }
}
