package com.example.academicsapp.service;

import com.example.academicsapp.dao.CourseDao;
import com.example.academicsapp.dao.FacultyDao;
import com.example.academicsapp.models.Course;
import com.example.academicsapp.models.Faculty;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {

    @Autowired
    private FacultyDao facultyDao;

    @Autowired
    private CourseDao courseDao;

    public List<Faculty> getAllFaculties(){
        return facultyDao.findAll();
    }

    public Faculty getFacultyById(Integer id){
        return facultyDao.findById(id).orElse(null);
    }

    public Faculty createFaculty(Faculty faculty){
        return facultyDao.save(faculty);
    }

    public Faculty updateFacultyById(Integer id, Faculty newFaculty){
        Faculty oldFaculty = facultyDao.findById(id).orElse(null);
        if(oldFaculty==null){
            return null;
        }
        BeanUtils.copyProperties(oldFaculty, newFaculty, "id");
        return facultyDao.save(oldFaculty);
    }

    public void deleteFacultyById(Integer id){
        facultyDao.deleteById(id);
    }

    public List<Course> getAllCoursesOfThisFaculty(Integer facultyId){
        return courseDao.getAllCoursesOfThisFaculty(facultyId);
    }
}
