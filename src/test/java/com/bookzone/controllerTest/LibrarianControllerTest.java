package com.bookzone.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bookzone.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import com.bookzone.controller.LibrarianController;
import com.bookzone.controller.RegistrationController;
import com.bookzone.model.Librarian;
import com.bookzone.service.RegistrationService;

@ExtendWith(MockitoExtension.class)
class LibrarianControllerTest {

    @Mock
    private LibrarianController librarianController;

    @Mock
    private RegistrationController registrationController;

    @Mock
    private RegistrationService registrationService;
    
    @Mock
    private PersonRepository personRepository;

    @Mock
    private Model model;
    
    @Mock
    private Librarian librarian1;
    
    @Mock
    private Librarian librarian2;

    @BeforeEach
	void setUp() throws Exception {
		librarianController = new LibrarianController();
		registrationService = new RegistrationService();
		librarian1 = new Librarian("John Brooks", "john_brooks", "john.brooks@sgbookcollectors.com", "OOO123mmm");
		librarian2 = new Librarian("Chris Evans","chris.evans", "chris.evans@gmailcom.com", "CHRisEVANs12344");
	}
    
    @Test
    void test_goToIndex() {
        String result = librarianController.goToIndex();
        assertEquals("index", result);
    }

    @Test
    void test_goToHome() {
        String result = librarianController.goToHome();
        assertEquals("home", result);
    }

    @Test
    void test_goToLogin() {
        String result = librarianController.goToLogin();
        assertEquals("login", result);
    }

    @Test
    void test_goToRegistration() {
        String result = registrationController.goToRegistration();
        assertEquals("registration", result);
    }
    
    @Test
    void test_logoutLibrarian() {
        String result = librarianController.logoutLibrarian();
        assertEquals("redirect:/", result);
    }

}
