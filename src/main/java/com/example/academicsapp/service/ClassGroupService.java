package com.example.academicsapp.service;

import com.example.academicsapp.models.ClassGroup;

import java.util.List;

public interface ClassGroupService {
    List<ClassGroup> getAllClassGroups();
    ClassGroup getClassGroupById(Integer id);
    ClassGroup createClassGroup(ClassGroup classGroup);
    ClassGroup updateClassGroup(Integer id, ClassGroup newClassGroup);
    void deleteClassGroupById(Integer id);
    ClassGroup updateFacultyOfClassGroup(Integer classGroupId, Integer facultyId);
}
