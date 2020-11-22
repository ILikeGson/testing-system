package org.example.service;

import org.example.domain.Student;
import org.springframework.stereotype.Service;

@Service("loginService")
public class StudentLoginService implements LoginService {
    private final ConsoleIOService consoleService;
    private final StudentService studentService;
    private final LocalizationService localizationService;

    public StudentLoginService(ConsoleIOService consoleService, StudentService studentService, LocalizationService localizationService) {
        this.consoleService = consoleService;
        this.studentService = studentService;
        this.localizationService = localizationService;
    }

    @Override
    public Student login() {
        consoleService.out(localizationService.getMessage("login.firstName"));
        String firstName = consoleService.read();
        consoleService.out(localizationService.getMessage("login.lastName"));
        String lastName = consoleService.read();
        return studentService.saveStudent(firstName, lastName);
    }
}
