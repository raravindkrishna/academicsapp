package com.example.academicsapp.service.ServiceImpls;

import com.example.academicsapp.dao.CourseDao;
import com.example.academicsapp.dao.FacultyDao;
import com.example.academicsapp.models.Course;
import com.example.academicsapp.models.Faculty;
import com.example.academicsapp.service.FacultyService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyDao facultyDao;

    @Autowired
    private CourseDao courseDao;

    @Override
    public List<Faculty> getAllFaculties(){
        return facultyDao.findAll();
    }

    @Override
    public Faculty getFacultyById(Integer id){
        if (id == null) {
            throw new IllegalArgumentException("Faculty id must not be null");
        }
        return facultyDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Faculty not found with id: " + id));
    }

    @Override
    public Faculty createFaculty(Faculty faculty){
        if (faculty == null || faculty.getFacultyName()==null) {
            throw new IllegalArgumentException("faculty object must not be null");
        }
        return facultyDao.save(faculty);
    }

    @Override
    public Faculty updateFacultyById(Integer id, Faculty newFaculty){
        if (id == null || newFaculty.getFacultyName()==null) {
            throw new IllegalArgumentException("faculty object must not be null");
        }

        Faculty oldFaculty = facultyDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Faculty not found with id: " + id));

        BeanUtils.copyProperties(newFaculty, oldFaculty, "id");
        return facultyDao.save(oldFaculty);
    }

    @Override
    public void deleteFacultyById(Integer id){

        if (id == null) {
            throw new IllegalArgumentException("Faculty id must not be null");
        }
        facultyDao.deleteById(id);
    }

    @Override
    public List<Course> getAllCoursesOfThisFaculty(Integer facultyId){
        if (facultyId == null) {
            throw new IllegalArgumentException("Faculty id must not be null");
        }
        return courseDao.getAllCoursesOfThisFaculty(facultyId);
    }
}
