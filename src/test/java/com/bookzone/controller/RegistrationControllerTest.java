package com.bookzone.controller;

import com.bookzone.exceptions.InvalidEmailAddressException;
import com.bookzone.exceptions.InvalidNameException;
import com.bookzone.exceptions.InvalidPasswordException;
import com.bookzone.exceptions.InvalidUsernameException;
import com.bookzone.exceptions.UnavailableEmailAddressException;
import com.bookzone.exceptions.UnavailableUsernameException;
import com.bookzone.model.Librarian;
import com.bookzone.model.Person;
import com.bookzone.service.RegistrationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistrationControllerTest {

    @InjectMocks
    private RegistrationController registrationController;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @Mock
    private RegistrationService registrationService;

    String validName;
    String validUsername;
    String validEmail;
    String validPassword;
    String viewName;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        validName = "John Tan";
        validUsername = "john_tan";
        validEmail = "john_tan@sgbookcollectors.com";
        validPassword = "SSSxxx12";
    }

    @Test
    void test_showRegistration() {
        assertEquals(registrationController.showRegistration(), "registration");
    }

    @Test
    void test_registrationSuccess() throws  InvalidNameException,
                                            InvalidEmailAddressException,
                                            InvalidPasswordException,
                                            InvalidUsernameException,
                                            UnavailableEmailAddressException,
                                            UnavailableUsernameException {
        // Arrange
        Person librarian = new Librarian(validName, validUsername, validEmail, validPassword);
        when(registrationService.registration(validName, validUsername, validEmail, validPassword)).thenReturn(librarian);

        // Act
        viewName = registrationController.registration(validName, validUsername, validEmail, validPassword, model, redirectAttributes);

        // Assert
        assertEquals("redirect:/login", viewName);
        verify(redirectAttributes).addFlashAttribute("successfulRegistration", "Registration successful. Please log in.");
        verifyNoInteractions(model);
    }

    @Test
    void test_registrationFailure_invalidName() throws  InvalidNameException,
                                                        InvalidEmailAddressException,
                                                        InvalidPasswordException,
                                                        InvalidUsernameException,
                                                        UnavailableEmailAddressException,
                                                        UnavailableUsernameException {
        // Arrange
        Person librarian = new Librarian("john", validUsername, validEmail, validPassword);
        when(registrationService.registration("john", validUsername, validEmail, validPassword)).thenThrow(InvalidNameException.class);

        // Act
        viewName = registrationController.registration("john", validUsername, validEmail, validPassword, model, redirectAttributes);

        // Assert
        assertEquals("registration", viewName);
        assertThrows(InvalidNameException.class, () -> registrationService.registration("john", validUsername, validEmail, validPassword));
        verify(model).addAttribute("error", "Unsuccessful registration due to invalid name.");
        verifyNoInteractions(redirectAttributes);
    }

    @Test
    void test_registrationFailure_invalidUsername() throws  InvalidNameException,
                                                            InvalidEmailAddressException,
                                                            InvalidPasswordException,
                                                            InvalidUsernameException,
                                                            UnavailableEmailAddressException,
                                                            UnavailableUsernameException {
        // Arrange
        Person librarian = new Librarian(validName, "ada", validEmail, validPassword);
        when(registrationService.registration(validName, "ada", validEmail, validPassword)).thenThrow(InvalidUsernameException.class);

        // Act
        viewName = registrationController.registration(validName, "ada", validEmail, validPassword, model, redirectAttributes);

        // Assert
        assertEquals("registration", viewName);
        assertThrows(InvalidUsernameException.class, () -> registrationService.registration(validName, "ada", validEmail, validPassword));
        verify(model).addAttribute("error", "Unsuccessful registration due to invalid username.");
        verifyNoInteractions(redirectAttributes);
    }

    @Test
    void test_registrationFailure_invalidEmailAddress() throws  InvalidNameException,
                                                                InvalidEmailAddressException,
                                                                InvalidPasswordException,
                                                                InvalidUsernameException,
                                                                UnavailableEmailAddressException,
                                                                UnavailableUsernameException {
        // Arrange
        Person librarian = new Librarian(validName, validUsername, "ada", validPassword);
        when(registrationService.registration(validName, validUsername, "ada", validPassword)).thenThrow(InvalidEmailAddressException.class);

        // Act
        viewName = registrationController.registration(validName, validUsername, "ada", validPassword, model, redirectAttributes);

        // Assert
        assertEquals("registration", viewName);
        assertThrows(InvalidEmailAddressException.class, () -> registrationService.registration(validName, validUsername, "ada", validPassword));
        verify(model).addAttribute("error", "Unsuccessful registration due to invalid email address.");
        verifyNoInteractions(redirectAttributes);
    }

    @Test
    void test_registrationFailure_invalidPassword() throws  InvalidNameException,
                                                            InvalidEmailAddressException,
                                                            InvalidPasswordException,
                                                            InvalidUsernameException,
                                                            UnavailableEmailAddressException,
                                                            UnavailableUsernameException {
        // Arrange
        Person librarian = new Librarian(validName, validUsername, validEmail, "ada");
        when(registrationService.registration(validName, validUsername, validEmail, "ada")).thenThrow(InvalidPasswordException.class);

        // Act
        viewName = registrationController.registration(validName, validUsername, validEmail, "ada", model, redirectAttributes);

        // Assert
        assertEquals("registration", viewName);
        assertThrows(InvalidPasswordException.class, () -> registrationService.registration(validName, validUsername, validEmail, "ada"));
        verify(model).addAttribute("error", "Unsuccessful registration due to invalid password.");
        verifyNoInteractions(redirectAttributes);
    }

    @Test
    void test_registrationFailure_unavailableUsername() throws  InvalidNameException,
                                                                InvalidEmailAddressException,
                                                                InvalidPasswordException,
                                                                InvalidUsernameException,
                                                                UnavailableEmailAddressException,
                                                                UnavailableUsernameException {
        // Arrange
        when(registrationService.registration(validName, validUsername, validEmail, validPassword)).thenThrow(UnavailableUsernameException.class);

        // Act
        viewName = registrationController.registration(validName, validUsername, validEmail, validPassword, model, redirectAttributes);

        // Assert
        assertEquals("registration", viewName);
        verify(model).addAttribute("error", "Username entered is unavailable. Please enter another username.");
        verifyNoInteractions(redirectAttributes);
    }

    @Test
    void test_registrationFailure_unavailableEmailAddress() throws  InvalidNameException,
                                                                    InvalidEmailAddressException,
                                                                    InvalidPasswordException,
                                                                    InvalidUsernameException,
                                                                    UnavailableEmailAddressException,
                                                                    UnavailableUsernameException {
        // Arrange
        when(registrationService.registration(validName, validUsername, validEmail, validPassword)).thenThrow(UnavailableEmailAddressException.class);

        // Act
        viewName = registrationController.registration(validName, validUsername, validEmail, validPassword, model, redirectAttributes);

        // Assert
        assertEquals("registration", viewName);
        verify(model).addAttribute("error", "Email address entered is unavailable. Please enter another email address.");
        verifyNoInteractions(redirectAttributes);
    }

}