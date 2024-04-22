package com.example.academicsapp.dao;

import com.example.academicsapp.models.ClassGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassGroupDao extends JpaRepository<ClassGroup,Integer> {

}
