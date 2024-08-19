package com.bookzone.controller;

import com.bookzone.exceptions.*;
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
    public void testRegistration_Success() throws InvalidNameException, InvalidEmailAddressException, UnavailableEmailAddressException, InvalidPasswordException, InvalidUsernameException, UnavailableUsernameException {
        // Arrange
        Person librarian = new Librarian(validName, validUsername, validEmail, validPassword);
        when(registrationService.registration(validName, validUsername, validEmail, validPassword)).thenReturn(librarian);

        // Act
        String viewName = registrationController.registration(validName, validUsername, validEmail, validPassword, model, redirectAttributes);

        // Assert
        assertEquals(viewName, "redirect:/login");
        verify(redirectAttributes).addFlashAttribute("successfulRegistration", "Registration successful. Please log in.");
        verifyNoInteractions(model);
    }

}