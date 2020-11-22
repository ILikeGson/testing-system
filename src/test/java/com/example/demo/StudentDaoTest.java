package com.example.demo;

import org.example.dao.StudentDao;
import org.example.dao.StudentDaoImpl;
import org.example.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class StudentDaoTest {

    @Test
    public void createStudent() {
        StudentDao dao = new StudentDaoImpl();
        Student student = dao.createStudent("Vasya", "Baboshin");
        Assert.notNull(student, "not null");
    }
}
