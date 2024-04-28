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
    public List<ClassGroup> getAllClassGroups(){
        return classGroupService.getAllClassGroups();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClassGroupById(@PathVariable Integer id){
        ClassGroup classGroup = classGroupService.getClassGroupById(id);
        if(classGroup==null){
            String errorMessage = "ClassGroup with id: "+ id + " is not found";
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(classGroup, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClassGroup> createClassGroup(@RequestBody ClassGroup classGroup){

        ClassGroup addedClassGroup = classGroupService.createClassGroup(classGroup);
        return new ResponseEntity<>(addedClassGroup, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassGroup> updateClassGroupById(@PathVariable Integer id, @RequestBody ClassGroup newClassGroup){

      ClassGroup updatedClassGroup = classGroupService.updateClassGroup(id, newClassGroup);

        if(updatedClassGroup==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedClassGroup, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteClassGroupById(@RequestParam("id") List<Integer> ids){
        for(Integer id : ids) {
            classGroupService.deleteClassGroupById(id);
        }

        return new ResponseEntity( HttpStatus.OK);
    }

    @PutMapping("/{classGroupId}/faculty/{facultyId}")
    public ResponseEntity updateFacultyOfClassGroup(@PathVariable Integer classGroupId, @PathVariable Integer facultyId){
        ClassGroup updatedClassGroup = classGroupService.updateFacultyOfClassGroup(classGroupId, facultyId);
        if(updatedClassGroup==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity (HttpStatus.OK);
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
