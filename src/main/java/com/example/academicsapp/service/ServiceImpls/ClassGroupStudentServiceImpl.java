package com.example.academicsapp.service.ServiceImpls;

import com.example.academicsapp.dao.ClassGroupDao;
import com.example.academicsapp.dao.ClassGroupStudentDao;
import com.example.academicsapp.dao.StudentDao;
import com.example.academicsapp.models.ClassGroup;
import com.example.academicsapp.models.ClassGroupStudent;
import com.example.academicsapp.models.Student;
import com.example.academicsapp.service.ClassGroupStudentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassGroupStudentServiceImpl implements ClassGroupStudentService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ClassGroupDao classGroupDao;
    @Autowired
    private ClassGroupStudentDao classGroupStudentDao;

    @Override
    public List<Student> getAllStudentsInClassGroup(Integer classGroupId) {
        if (classGroupId == null ) {
            throw new IllegalArgumentException("ClassGroup id must not be null");
        }
        return studentDao.findAllByClassGroupId(classGroupId);
    }

    @Override
    public ClassGroupStudent addStudentToClassGroup(Integer classGroupId, Integer studentId){
        if (classGroupId == null || studentId==null) {
            throw new IllegalArgumentException("student object must not be null");
        }
        ClassGroup classGroup = classGroupDao.findById(classGroupId).orElseThrow(() -> new EntityNotFoundException("ClassGroup not found with id: " + classGroupId));
        Student student = studentDao.findById(studentId).orElseThrow(() -> new EntityNotFoundException("Faculty not found with id: " + studentId));
        if(classGroup==null || student==null){
            throw new IllegalArgumentException("ClassGroup or student object must not be null");
        }

        if (classGroupStudentDao.findByClassGroupAndStudent(classGroup, student).isPresent()) {
            throw new RuntimeException("Student already enrolled");
        }

        ClassGroupStudent classGroupStudent = new ClassGroupStudent();
        classGroupStudent.setClassGroup(classGroup);
        classGroupStudent.setStudent(student);
        return classGroupStudentDao.save(classGroupStudent);
    }

    @Override
    public void removeStudentsFromClassGroup(Integer classGroupId,List<Integer> ids){
        if (classGroupId == null) {
            throw new IllegalArgumentException("classGroup id must not be null");
        }
        ClassGroup classGroup = classGroupDao.findById(classGroupId).orElseThrow(() -> new EntityNotFoundException("ClassGroup not found with id: " + classGroupId));

        for(Integer id: ids){
            if (id == null) {
                throw new IllegalArgumentException("Student id must not be null");
            }
            Student student = studentDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));
            ClassGroupStudent classGroupStudent = classGroupStudentDao.findByClassGroupAndStudent(classGroup, student).orElse(null);
            if(classGroupStudent==null){
                continue;
            }
            classGroupStudentDao.delete(classGroupStudent);
        }

    }
}
