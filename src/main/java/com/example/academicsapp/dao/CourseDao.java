package com.example.academicsapp.dao;


import com.example.academicsapp.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDao extends JpaRepository<Course, Integer> {

    @Query(value = """
            Select distinct c.* from course c
                                join class_group cg  on  c.id=cg.course_id
                                join  faculty f  on  cg.faculty_id =f.id where f.id=?1
            """,nativeQuery = true)
    List<Course> getAllCoursesOfThisFaculty(Integer facultyId);
}
