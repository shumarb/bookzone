package com.bookzone.controller;

import com.bookzone.exceptions.UnsuccessfulLoginException;
import com.bookzone.model.Librarian;
import com.bookzone.model.Person;
import com.bookzone.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;

    @Mock
    private HttpSession httpSession;

    @Mock
    private LoginService loginService;

    @Mock
    private Model model;

    @Mock
    Person person;

    String invalidEmail;
    String invalidPassword;
    String validEmail;
    String validPassword;
    String viewName;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        invalidEmail = "john_tan@sgbookcollectors.com";
        invalidPassword = "bbb";
        validEmail = "ali.hassan@sgbookcollectors.com";
        validPassword = "HelloWorld34";
        person = new Librarian("Ali Hassan", "ali_hassan", validEmail, validPassword);
    }

    @Test
    void returnLoginViewWhenLoginIsCalled() {
        assertEquals("login", loginController.showLogin());
    }

    @Test
    void redirectToHomeForSuccessfulLogin() throws UnsuccessfulLoginException {
        // Arrange
        when(loginService.login(validEmail, validPassword)).thenReturn(person);

        // Act
        viewName = loginController.login(validEmail, validPassword, httpSession, model);

        // Assert
        assertEquals("redirect:/home", viewName);
    }

    @Test
    void returnLoginViewForLoginFailureDueToIncorrectEmailAddress() throws UnsuccessfulLoginException {
        // Arrange
        when(loginService.login(invalidEmail, validPassword)).thenThrow(UnsuccessfulLoginException.class);

        // Act
        viewName = loginController.login(invalidEmail, validPassword, httpSession, model);

        // Assert
        assertEquals("login", viewName);
        assertThrows(UnsuccessfulLoginException.class, () -> loginService.login(invalidEmail, validPassword));
        verify(model).addAttribute("error", "Invalid email address or password. Please try again.");
    }

    @Test
    void returnLoginViewForLoginFailureDueToIncorrectPassword() throws UnsuccessfulLoginException {
        // Arrange
        when(loginService.login(validEmail, invalidPassword)).thenThrow(UnsuccessfulLoginException.class);

        // Act
        viewName = loginController.login(validEmail, invalidPassword, httpSession, model);

        // Assert
        assertEquals("login", viewName);
        assertThrows(UnsuccessfulLoginException.class, () -> loginService.login(validEmail, invalidPassword));
        verify(model).addAttribute("error", "Invalid email address or password. Please try again.");
    }

    @Test
    void returnLoginViewForLoginFailureDueToIncorrectEmailAddressAndPassword() throws UnsuccessfulLoginException {
        // Arrange
        when(loginService.login(invalidEmail, invalidPassword)).thenThrow(UnsuccessfulLoginException.class);

        // Act
        viewName = loginController.login(invalidEmail, invalidPassword, httpSession, model);

        // Assert
        assertEquals("login", viewName);
        assertThrows(UnsuccessfulLoginException.class, () -> loginService.login(invalidEmail, invalidPassword));
        verify(model).addAttribute("error", "Invalid email address or password. Please try again.");
    }

    @Test
    void returnLoginViewForLoginFailureDueToUnexpectedError() throws UnsuccessfulLoginException {
        // Arrange
        when(loginService.login(validEmail, validPassword)).thenThrow(new RuntimeException());

        // Act
        viewName = loginController.login(validEmail, validPassword, httpSession, model);

        // Assert
        assertEquals("login", viewName);
        assertThrows(RuntimeException.class, () -> loginService.login(validEmail, validPassword));
        verify(model).addAttribute("error", "Unexpected error occurred. Please try again later.");
    }

}