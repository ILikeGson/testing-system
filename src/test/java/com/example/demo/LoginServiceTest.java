package com.example.demo;

import org.example.service.ConsoleIOService;
import org.example.domain.Student;
import org.example.service.LocalizationService;
import org.example.service.StudentLoginService;
import org.example.service.StudentServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class LoginServiceTest {

    @Mock
    LocalizationService localizationService;

    @Mock
    ConsoleIOService consoleIOService;

    @Mock
    StudentServiceImpl studentService;

    @InjectMocks
    StudentLoginService loginService;

    @DisplayName("testing a login system")
    @Test
    public void testStudentLogin() {
        when(loginService.login()).thenReturn(new Student("Vasya", "Ivanov"));
        assertNotNull(loginService.login());
    }
}
