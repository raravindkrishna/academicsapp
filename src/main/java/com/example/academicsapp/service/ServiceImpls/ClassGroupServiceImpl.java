package com.example.academicsapp.service.ServiceImpls;

import com.example.academicsapp.dao.ClassGroupDao;
import com.example.academicsapp.dao.CourseDao;
import com.example.academicsapp.dao.FacultyDao;
import com.example.academicsapp.models.ClassGroup;
import com.example.academicsapp.models.Faculty;
import com.example.academicsapp.service.ClassGroupService;
import jakarta.persistence.EntityNotFoundException;
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
        if (classGroup == null || classGroup.getClassGroupName() == null || classGroup.getFaculty() == null || classGroup.getCourse() == null) {
            throw new IllegalArgumentException("ClassGroup object, classGroupName, faculty, and course cannot be null");
        }
        try {
            return classGroupDao.save(classGroup);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while creating ClassGroup", e);
        }
    }

    @Override
    public ClassGroup updateClassGroup(Integer id, ClassGroup newClassGroup) {
        if (newClassGroup.getClassGroupName() == null ) {
            throw new IllegalArgumentException("New classGroupName cannot be null");
        }
        try {
            ClassGroup oldClassGroup = classGroupDao.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("ClassGroup not found with id: " + id));
            BeanUtils.copyProperties(newClassGroup, oldClassGroup, "id", "faculty", "course");
            return classGroupDao.save(oldClassGroup);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while updating ClassGroup with id: " + id, e);
        }
    }
    @Override
    public void deleteClassGroupById(Integer id){
        try {
            classGroupDao.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while deleting ClassGroup with id: " + id, e);
        }
    }
    @Override
    public ClassGroup updateFacultyOfClassGroup(Integer classGroupId, Integer facultyId){
        if (classGroupId == null || facultyId == null) {
            throw new IllegalArgumentException("ClassGroupId and FacultyId must not be null");
        }

        Faculty newFaculty = facultyDao.findById(facultyId)
                .orElseThrow(() -> new EntityNotFoundException("Faculty not found with id: " + facultyId));
        ClassGroup updatedClassGroup = classGroupDao.findById(classGroupId)
                .orElseThrow(() -> new EntityNotFoundException("ClassGroup not found with id: " + classGroupId));

        updatedClassGroup.setFaculty(newFaculty);
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
