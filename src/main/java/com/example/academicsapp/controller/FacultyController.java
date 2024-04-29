package com.example.academicsapp.controller;

import com.example.academicsapp.models.Course;
import com.example.academicsapp.models.Faculty;
import com.example.academicsapp.service.ServiceImpls.FacultyServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {

    @Autowired
    FacultyServiceImpl facultyService;
    @GetMapping
    public ResponseEntity<List<Faculty>> getAllFaculties() {
        try {
            List<Faculty> faculty = facultyService.getAllFaculties();
            return new ResponseEntity<>(faculty, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching faculties", e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable Integer id){

        try {
            Faculty faculty = facultyService.getFacultyById(id);
            return new ResponseEntity<>(faculty, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching faculties with id: " + id, e);
        }
    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty){

        try {
            Faculty addedFaculty = facultyService.createFaculty(faculty);
            return new ResponseEntity<>(addedFaculty, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while creating Faculty", e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faculty> updateFacultyById(@PathVariable Integer id, @RequestBody Faculty faculty){
        try {
            Faculty updatedFaculty = facultyService.updateFacultyById(id, faculty);
            return new ResponseEntity<>(updatedFaculty, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while updating faculty with id: " + id, e);
        }
    }

    @DeleteMapping
    public ResponseEntity deleteFacultyById(@RequestParam("id") List<Integer> ids) {
     try {
        for (Integer id : ids) {
            facultyService.deleteFacultyById(id);
        }
        return new ResponseEntity(HttpStatus.OK);
    } catch (IllegalArgumentException e) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
        throw new RuntimeException("Error occurred while deleting faculties", e);
    }
    }


    @GetMapping("/{facultyId}/courses")
    public ResponseEntity<List<Course>> getAllCoursesOfThisFaculty(@PathVariable Integer facultyId){
        try {
            List<Course> courses =  facultyService.getAllCoursesOfThisFaculty(facultyId);
            return new ResponseEntity<>(courses, HttpStatus.OK);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            throw new RuntimeException("Error occurred while updating course with id: " + facultyId, e);
        }
    }
}
