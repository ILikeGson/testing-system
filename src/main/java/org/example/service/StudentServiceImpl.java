package org.example.service;

import org.example.dao.StudentDao;
import org.example.domain.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentDao dao;

    public StudentServiceImpl(StudentDao dao) {
        this.dao = dao;
    }

    @Override
    public Student saveStudent(String firstName, String lastName) {
        return dao.createStudent(firstName, lastName);
    }
}
