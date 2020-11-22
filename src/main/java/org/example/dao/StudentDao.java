package org.example.dao;

import org.example.domain.Student;

public interface StudentDao {
    Student createStudent(String firstName, String lastName);
}
