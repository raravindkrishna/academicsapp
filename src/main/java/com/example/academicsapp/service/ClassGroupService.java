package com.example.academicsapp.service;

import com.example.academicsapp.dao.ClassGroupDao;
import com.example.academicsapp.dao.CourseDao;
import com.example.academicsapp.dao.FacultyDao;
import com.example.academicsapp.models.ClassGroup;
import com.example.academicsapp.models.Course;
import com.example.academicsapp.models.Faculty;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassGroupService {

    @Autowired
    private ClassGroupDao classGroupDao;

    @Autowired
    private FacultyDao facultyDao;

    @Autowired
    private CourseDao courseDao;

    public List<ClassGroup> getAllClassGroups(){
        return classGroupDao.findAll();
    }

    public ClassGroup getClassGroupById(Integer id){
        return classGroupDao.findById(id).orElse(null);
    }
    public ClassGroup createClassGroup(ClassGroup classGroup){
        return classGroupDao.save(classGroup);
    }

    public ClassGroup updateClassGroup(Integer id, ClassGroup newClassGroup) {
        ClassGroup oldClassGroup = classGroupDao.findById(id).orElse(null);

        if(oldClassGroup==null){
            return null;
        }
        BeanUtils.copyProperties(newClassGroup, oldClassGroup, "id");
        return classGroupDao.save(oldClassGroup);
    }
    public void deleteClassGroupById(Integer id){
        classGroupDao.deleteById(id);
    }

    public ClassGroup updateFacultyOfClassGroup(Integer classGroupId, Integer facultyId){
        Faculty newfaculty = facultyDao.findById(facultyId).orElse(null);
        ClassGroup updatedClassGroup = classGroupDao.findById(classGroupId).orElse(null);

        if(newfaculty==null || updatedClassGroup==null){
            return null;
        }
        updatedClassGroup.setFaculty(newfaculty);
        return classGroupDao.save(updatedClassGroup);
    }

    public ClassGroup updateCourseOfClassGroup(Integer classGroupId, Integer courseId){
        Course newCourse = courseDao.findById(courseId).orElse(null);
        ClassGroup updatedClassGroup = classGroupDao.findById(classGroupId).orElse(null);
        if(newCourse==null || updatedClassGroup==null){
            return null;
        }
        updatedClassGroup.setCourse(newCourse);
        return classGroupDao.save(updatedClassGroup);
    }
}
