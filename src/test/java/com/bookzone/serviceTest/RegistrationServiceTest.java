package com.bookzone.serviceTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.bookzone.service.RegistrationService;
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
    void testIsValidEmailAddress_validExistingEmail() {
        String email = "john.tan@sgbookcollectors.com";
        when(personRepository.findByEmail(email)).thenReturn(Optional.of(new Librarian()));
        boolean result = registrationService.isValidEmailAddress(email);
        assertFalse(result);
    }

    @Test
    void testIsValidEmailAddress_validNewEmail() {
        String email = "john.wayne@sgbookcollectors.com";
        when(personRepository.findByEmail(email)).thenReturn(Optional.empty());
        boolean result = registrationService.isValidEmailAddress(email);
        assertTrue(result);
    }

    @Test
    void testIsValidPassword_validPassword() {
        String password = "SSSSSSSnnnnn981231";
        boolean result = registrationService.isValidPassword(password);
        assertTrue(result);
    }

    @Test
    void testIsValidPassword_invalidPassword_insufficientUpperCaseLetters() {
        String password = "WWmmm11";
        boolean result = registrationService.isValidPassword(password);
        assertFalse(result);
    }

    @Test
    void testIsValidName_validName() {
        String name = "John Doe";
        boolean result = registrationService.isValidName(name);
        assertTrue(result);
    }

    @Test
    void testIsValidName_InvalidName() {
        String name = "John";
        boolean result = registrationService.isValidName(name);
        assertFalse(result);
    }

    @Test
    void testLoginLibrarian_existingEmailAndWrongPassword() {
        String email = "john.tan@sgbookcollectors.com";
        String password = "PPPlll333";
        Librarian librarian = new Librarian();
        librarian.setPassword("StrongPassword");
        when(personRepository.findByEmail(email)).thenReturn(Optional.of(librarian));

        boolean result = registrationService.loginLibrarian(email, password);
        assertFalse(result);
    }

}

