package com.example.demo;

import org.example.dao.StudentDao;
import org.example.dao.StudentDaoImpl;
import org.example.domain.Student;
import org.example.service.StudentService;
import org.example.service.StudentServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Testing studentService ")
@SpringBootTest
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;

    @Configuration
    static class StudentServiceConfig {
        @Bean
        public StudentDao studentDao(){
            return new StudentDaoImpl();
        }

        @Bean
        public StudentService studentService(StudentDao studentDao) {
            return new StudentServiceImpl(studentDao);
        }
    }

    @DisplayName("saving a student...")
    @Test
    public void saveStudent() {
        String firstName = "Oleg";
        String lastName = "Bibi";
        Student student = studentService.saveStudent(firstName, lastName);
        Assert.notNull(student, "student was saved");
        assertThat(student.getLastName()).isEqualTo("Bibi");
        assertThat(student.getFirstName()).isEqualTo("Oleg");
        assertThat(student).hasNoNullFieldsOrProperties();
    }
}
