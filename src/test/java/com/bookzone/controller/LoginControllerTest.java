package com.bookzone.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;

    String validEmail;
    String validPassword;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        validEmail = "ali.hassan@sgbookcollectors.com";
        validPassword = "MMMnnn34";
    }

    @Test
    void test_showLogin() {
        assertEquals("login", loginController.showLogin());
    }


}