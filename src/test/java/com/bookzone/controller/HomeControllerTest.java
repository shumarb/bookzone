package com.bookzone.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bookzone.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import com.bookzone.model.Librarian;
import com.bookzone.service.RegistrationService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ExtendWith(MockitoExtension.class)
class HomeControllerTest {

    @Mock
    private HomeController homeController;

    @Mock
    private RegistrationController registrationController;

    @Mock
    private RedirectAttributes redirectAttributes;

    @Mock
    private RegistrationService registrationService;
    
    @Mock
    private PersonRepository personRepository;

    @Mock
    private HttpSession httpSession;

    @Mock
    private Model model;
    
    @Mock
    private Librarian librarian1;
    
    @Mock
    private Librarian librarian2;

    @BeforeEach
	void setUp() throws Exception {
		homeController = new HomeController();
		registrationService = new RegistrationService();
		librarian1 = new Librarian("John Brooks", "john_brooks", "john.brooks@sgbookcollectors.com", "OOO123mmm");
		librarian2 = new Librarian("Chris Evans","chris.evans", "chris.evans@gmailcom.com", "CHRisEVANs12344");
	}
    
    @Test
    void test_showHome() {
        String result = homeController.showHome(httpSession, model);
        assertEquals("home", result);
    }

    @Test
    void test_goToRegistration() {
        String result = registrationController.goToRegistration();
        assertEquals("registration", result);
    }

}
