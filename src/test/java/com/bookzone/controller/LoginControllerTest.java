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
        validPassword = "MMMnnn34";
        person = new Librarian("Ali Hassan", "ali_hassan", validEmail, validPassword);
    }

    @Test
    void test_showLogin() {
        assertEquals("login", loginController.showLogin());
    }

    @Test
    void test_loginSuccess() throws UnsuccessfulLoginException {
        // Arrange
        when(loginService.login(validEmail, validPassword)).thenReturn(person);

        // Act
        viewName = loginController.login(validEmail, validPassword, httpSession, model);

        // Assert
        assertEquals("redirect:/home", viewName);
    }

    @Test
    void test_loginFailure_incorrectEmailAddress() throws UnsuccessfulLoginException {
        // Arrange
        when(loginService.login(invalidEmail, validPassword)).thenThrow(UnsuccessfulLoginException.class);

        // Act
        viewName = loginController.login(invalidEmail, validPassword, httpSession, model);

        // Assert
        assertEquals("login", viewName);
        assertThrows(UnsuccessfulLoginException.class, () -> loginService.login(invalidEmail, validPassword));
        verify(model).addAttribute("error", "Invalid email or password. Please try again.");
    }

    @Test
    void test_loginFailure_incorrectPassword() throws UnsuccessfulLoginException {
        // Arrange
        when(loginService.login(validEmail, invalidPassword)).thenThrow(UnsuccessfulLoginException.class);

        // Act
        viewName = loginController.login(validEmail, invalidPassword, httpSession, model);

        // Assert
        assertEquals("login", viewName);
        assertThrows(UnsuccessfulLoginException.class, () -> loginService.login(validEmail, invalidPassword));
        verify(model).addAttribute("error", "Invalid email or password. Please try again.");
    }

}