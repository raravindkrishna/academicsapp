package com.example.academicsapp.controller;

import com.example.academicsapp.models.ClassGroup;
import com.example.academicsapp.service.ServiceImpls.ClassGroupServiceImpl;
import com.example.academicsapp.service.ServiceImpls.FacultyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classGroup")
public class ClassGroupController {

    @Autowired
    private ClassGroupServiceImpl classGroupService;

    @Autowired
    private FacultyServiceImpl facultyService;

    @GetMapping
    public ResponseEntity<List<ClassGroup>> getAllClassGroups() {
        try {
            List<ClassGroup> classGroups = classGroupService.getAllClassGroups();
            return new ResponseEntity<>(classGroups, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching class groups", e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassGroup> getClassGroupById(@PathVariable Integer id) {
        try {
            ClassGroup classGroup = classGroupService.getClassGroupById(id);
            if (classGroup == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(classGroup, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching class group with id: " + id, e);
        }
    }

    @PostMapping
    public ResponseEntity<ClassGroup> createClassGroup(@RequestBody ClassGroup classGroup) {
        try {
            ClassGroup addedClassGroup = classGroupService.createClassGroup(classGroup);
            return new ResponseEntity<>(addedClassGroup, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while creating class group", e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassGroup> updateClassGroupById(@PathVariable Integer id, @RequestBody ClassGroup newClassGroup) {
        try {
            ClassGroup updatedClassGroup = classGroupService.updateClassGroup(id, newClassGroup);
            if (updatedClassGroup == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(updatedClassGroup, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while updating class group with id: " + id, e);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteClassGroupById(@RequestParam("id") List<Integer> ids) {
        try {
            for (Integer id : ids) {
                classGroupService.deleteClassGroupById(id);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while deleting class group(s)", e);
        }
    }

    @PutMapping("/{classGroupId}/faculty/{facultyId}")
    public ResponseEntity<?> updateFacultyOfClassGroup(@PathVariable Integer classGroupId, @PathVariable Integer facultyId) {
        try {
            ClassGroup updatedClassGroup = classGroupService.updateFacultyOfClassGroup(classGroupId, facultyId);
            if (updatedClassGroup == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while updating faculty for class group with id: " + classGroupId, e);
        }
    }
    /*
Not allowed
    @PutMapping("{classGroupId}/course/{courseId}")
    public ResponseEntity<String> updateCourseOfClassGroup(@PathVariable Integer classGroupId, @PathVariable Integer courseId){
        ClassGroup updatedClassGroup = classGroupService.updateCourseOfClassGroup(classGroupId, courseId);
        if(updatedClassGroup==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String message = "Updated Successfully";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
*/
}
