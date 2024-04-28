package com.example.academicsapp.service.ServiceImpls;

import com.example.academicsapp.dao.CourseDao;
import com.example.academicsapp.dao.FacultyDao;
import com.example.academicsapp.models.Course;
import com.example.academicsapp.models.Faculty;
import com.example.academicsapp.service.FacultyService;
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
        return facultyDao.findById(id).orElse(null);
    }

    @Override
    public Faculty createFaculty(Faculty faculty){
        return facultyDao.save(faculty);
    }

    @Override
    public Faculty updateFacultyById(Integer id, Faculty newFaculty){
        Faculty oldFaculty = facultyDao.findById(id).orElse(null);
        if(oldFaculty==null){
            return null;
        }
        BeanUtils.copyProperties(newFaculty, oldFaculty, "id");
        return facultyDao.save(oldFaculty);
    }

    @Override
    public void deleteFacultyById(Integer id){
        facultyDao.deleteById(id);
    }

    @Override
    public List<Course> getAllCoursesOfThisFaculty(Integer facultyId){
        return courseDao.getAllCoursesOfThisFaculty(facultyId);
    }
}
