package com.example.academicsapp.service.ServiceImpls;

import com.example.academicsapp.dao.ClassGroupDao;
import com.example.academicsapp.dao.CourseDao;
import com.example.academicsapp.dao.FacultyDao;
import com.example.academicsapp.models.ClassGroup;
import com.example.academicsapp.models.Faculty;
import com.example.academicsapp.service.ClassGroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassGroupServiceImpl implements ClassGroupService {

    @Autowired
    private ClassGroupDao classGroupDao;

    @Autowired
    private FacultyDao facultyDao;

    @Autowired
    private CourseDao courseDao;

    @Override
    public List<ClassGroup> getAllClassGroups(){
        return classGroupDao.findAll();
    }
    @Override
    public ClassGroup getClassGroupById(Integer id){
        return classGroupDao.findById(id).orElse(null);
    }
    @Override
    public ClassGroup createClassGroup(ClassGroup classGroup){
        return classGroupDao.save(classGroup);
    }

    @Override
    public ClassGroup updateClassGroup(Integer id, ClassGroup newClassGroup) {
        ClassGroup oldClassGroup = classGroupDao.findById(id).orElse(null);

        if(oldClassGroup==null){
            return null;
        }
        BeanUtils.copyProperties(newClassGroup, oldClassGroup, "id");
        return classGroupDao.save(oldClassGroup);
    }
    @Override
    public void deleteClassGroupById(Integer id){
        classGroupDao.deleteById(id);
    }
    @Override
    public ClassGroup updateFacultyOfClassGroup(Integer classGroupId, Integer facultyId){
        Faculty newfaculty = facultyDao.findById(facultyId).orElse(null);
        ClassGroup updatedClassGroup = classGroupDao.findById(classGroupId).orElse(null);

        if(newfaculty==null || updatedClassGroup==null){
            return null;
        }
        updatedClassGroup.setFaculty(newfaculty);
        return classGroupDao.save(updatedClassGroup);
    }
/*
Not allowed
    public ClassGroup updateCourseOfClassGroup(Integer classGroupId, Integer courseId){
        Course newCourse = courseDao.findById(courseId).orElse(null);
        ClassGroup updatedClassGroup = classGroupDao.findById(classGroupId).orElse(null);
        if(newCourse==null || updatedClassGroup==null){
            return null;
        }
        updatedClassGroup.setCourse(newCourse);
        return classGroupDao.save(updatedClassGroup);
    }
*/
}
