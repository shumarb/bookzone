package com.bookzone.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bookzone.model.Librarian;
import com.bookzone.repository.PersonRepository;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private RegistrationService registrationService;

    @Test
    void test_isValidEmailAddress_validExistingEmail() {
        String email = "john.tan@sgbookcollectors.com";
        lenient().when(personRepository.findByEmail(email)).thenReturn(Optional.of(new Librarian()));
        boolean result = registrationService.isValidEmailAddress(email);
        assertTrue(result);
    }

    @Test
    void test_isValidEmailAddress_validNewEmail() {
        String email = "john.wayne@sgbookcollectors.com";
        when(personRepository.findByEmail(email)).thenReturn(Optional.empty());
        boolean result = registrationService.isValidEmailAddress(email);
        assertTrue(result);
    }

    @Test
    void test_sValidPassword_validPassword() {
        String password = "SSSSSSSnnnnn981231";
        boolean result = registrationService.isValidPassword(password);
        assertTrue(result);
    }

    @Test
    void test_isValidPassword_invalidPassword_insufficientUpperCaseLetters() {
        String password = "WWmmm11";
        boolean result = registrationService.isValidPassword(password);
        assertFalse(result);
    }

    @Test
    void test_isValidName_validName() {
        String name = "John Doe";
        boolean result = registrationService.isValidName(name);
        assertTrue(result);
    }

    @Test
    void test_isValidName_invalidName() {
        String name = "John";
        boolean result = registrationService.isValidName(name);
        assertFalse(result);
    }

}

