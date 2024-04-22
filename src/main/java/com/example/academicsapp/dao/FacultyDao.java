package com.example.academicsapp.dao;

import com.example.academicsapp.models.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyDao extends JpaRepository<Faculty, Integer> {

    @Query(value = """ 
                    Select distinct f.* from faculty f
                    join class_group cg on   f.id=cg.faculty_id
                    join  course c  on  cg.course_id=c.id where c.id=?1""",nativeQuery = true)
    List<Faculty> getAllFacultiesOfThisCourse(Integer courseId);
}
