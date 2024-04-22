package com.example.academicsapp.service;

import com.example.academicsapp.dao.ClassGroupDao;
import com.example.academicsapp.dao.ClassGroupStudentDao;
import com.example.academicsapp.dao.StudentDao;
import com.example.academicsapp.models.ClassGroup;
import com.example.academicsapp.models.ClassGroupStudent;
import com.example.academicsapp.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassGroupStudentService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ClassGroupDao classGroupDao;
    @Autowired
    private ClassGroupStudentDao classGroupStudentDao;

    public List<Student> getAllStudentsInClassGroup(Integer classGroupId) {
        return studentDao.findAllByClassGroupId(classGroupId);
    }

    public String addStudentToClassGroup(Integer classGroupId, Integer studentId){
        ClassGroup classGroup = classGroupDao.findById(classGroupId).orElse(null);
        Student student = studentDao.findById(studentId).orElse(null);
        if(classGroup==null || student==null){
            return "ClassGroup or Student does not exist";
        }

        if (classGroupStudentDao.findByClassGroupAndStudent(classGroup, student).isPresent()) {
            return "Student already enrolled";
        }

        ClassGroupStudent classGroupStudent = new ClassGroupStudent();
        classGroupStudent.setClassGroup(classGroup);
        classGroupStudent.setStudent(student);
        classGroupStudentDao.save(classGroupStudent);

        return "Student is added to ClassGroup";
    }

    public String removeStudentsFromClassGroup(Integer classGroupId,List<Integer> ids){
        ClassGroup classGroup = classGroupDao.findById(classGroupId).orElse(null);

        for(Integer id: ids){
            Student student = studentDao.findById(id).orElse(null);
            ClassGroupStudent classGroupStudent = classGroupStudentDao.findByClassGroupAndStudent(classGroup, student).orElse(null);
            if(classGroupStudent==null){
                continue;
            }
            classGroupStudentDao.delete(classGroupStudent);
        }
        return "Students mentioned are removed from the ClassGroup";
    }
}
