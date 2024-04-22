package com.example.academicsapp.dao;

import com.example.academicsapp.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao extends JpaRepository<Student, Integer> {
    @Query(value = """
            SELECT s.*
            FROM class_group cg
            JOIN class_group_student cs ON cg.id = cs.class_group_id
            JOIN student s ON cs.student_id = s.id
            WHERE cg.id = ?1""", nativeQuery = true)
    List<Student> findAllByClassGroupId(Integer classGroupId);
}
