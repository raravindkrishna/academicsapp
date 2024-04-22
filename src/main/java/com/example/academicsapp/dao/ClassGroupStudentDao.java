package com.example.academicsapp.dao;

import com.example.academicsapp.models.ClassGroup;
import com.example.academicsapp.models.ClassGroupStudent;
import com.example.academicsapp.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassGroupStudentDao extends JpaRepository<ClassGroupStudent,Integer> {
    Optional<ClassGroupStudent> findByClassGroupAndStudent(ClassGroup classGroup, Student student);
}
