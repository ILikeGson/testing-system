package org.example.shell;

import org.example.service.ConsoleIOService;
import org.example.domain.Student;
import org.example.service.LoginService;
import org.example.service.TestingService;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
public class ApplicationShellCommands {
    private Student student;
    private final LoginService loginService;
    private final TestingService testingService;
    private final ConsoleIOService consoleService;

    public ApplicationShellCommands(LoginService loginService, TestingService testingService, ConsoleIOService consoleService) {
        this.loginService = loginService;
        this.testingService = testingService;
        this.consoleService = consoleService;
    }

    @ShellMethod(value = "Login command", key = {"login"})
    public void login() {
        student = loginService.login();
    }

    @ShellMethod(value = "Test command", key = {"test"})
    public void test() {
        consoleService.out(testingService.test(student));
        return;
    }

    @ShellMethodAvailability(value = "test")
    private Availability isStudentHasLoggedIn() {
        return student == null ? Availability.unavailable("Log in first") : Availability.available();
    }
}
