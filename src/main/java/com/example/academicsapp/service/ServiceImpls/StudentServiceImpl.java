package com.example.academicsapp.service.ServiceImpls;

import com.example.academicsapp.dao.StudentDao;
import com.example.academicsapp.models.Student;
import com.example.academicsapp.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public List<Student> getAllStudents(){
        return studentDao.findAll();
    }

    @Override
    public Student getStudentById(Integer id){
        return studentDao.findById(id).orElse(null);
    }

    @Override
    public Student createStudent(Student student){
        return studentDao.save(student);
    }

    @Override
    public Student updateStudentById(Integer id, Student newStudent){
        Student oldStudent = studentDao.findById(id).orElse(null);
        if(oldStudent==null){
            return null;
        }
        BeanUtils.copyProperties(newStudent, oldStudent, "id");
        return studentDao.save(oldStudent);
    }

    @Override
    public void deleteStudentById(Integer id){
        studentDao.deleteById(id);
    }

}
