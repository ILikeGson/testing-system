package org.example.dao;

import org.example.domain.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentDaoImpl implements StudentDao {

    @Override
    public Student createStudent(String firstName, String lastName) {
        return new Student(firstName, lastName);
    }
}
