package com.example.academicsapp.service.ServiceImpls;

import com.example.academicsapp.dao.StudentDao;
import com.example.academicsapp.models.Student;
import com.example.academicsapp.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
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
        if (id == null) {
            throw new IllegalArgumentException("Student id must not be null");
        }
        return studentDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));
    }

    @Override
    public Student createStudent(Student student){
        if (student == null || student.getStudentName()==null || student.getRollNo()==null) {
            throw new IllegalArgumentException("student object must not be null");
        }
        return studentDao.save(student);
    }

    @Override
    public Student updateStudentById(Integer id, Student newStudent){
        if (newStudent == null || newStudent.getStudentName()==null || newStudent.getRollNo()==null) {
            throw new IllegalArgumentException("student object must not be null");
        }
        Student oldStudent = studentDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));

        BeanUtils.copyProperties(newStudent, oldStudent, "id");
        return studentDao.save(oldStudent);
    }

    @Override
    public void deleteStudentById(Integer id){
        if (id == null) {
            throw new IllegalArgumentException("Student id must not be null");
        }
        studentDao.deleteById(id);
    }

}
