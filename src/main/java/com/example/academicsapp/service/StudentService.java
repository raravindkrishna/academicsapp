package com.example.academicsapp.service;

import com.example.academicsapp.dao.StudentDao;
import com.example.academicsapp.models.Student;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    public List<Student> getAllStudents(){
        return studentDao.findAll();
    }

    public Student getStudentById(Integer id){
        return studentDao.findById(id).orElse(null);
    }

    public Student createStudent(Student student){
        return studentDao.save(student);
    }

    public Student updateStudentById(Integer id, Student newStudent){
        Student oldStudent = studentDao.findById(id).orElse(null);
        if(oldStudent==null){
            return null;
        }
        BeanUtils.copyProperties(oldStudent, newStudent, "id");
        return studentDao.save(oldStudent);
    }

    public void deleteStudentById(Integer id){
        studentDao.deleteById(id);
    }

}
