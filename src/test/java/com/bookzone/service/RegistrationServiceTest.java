package com.bookzone.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.bookzone.exceptions.InvalidEmailAddressException;
import com.bookzone.exceptions.InvalidNameException;
import com.bookzone.exceptions.InvalidPasswordException;
import com.bookzone.exceptions.InvalidUsernameException;
import com.bookzone.exceptions.UnavailableEmailAddressException;
import com.bookzone.exceptions.UnavailableUsernameException;
import com.bookzone.model.Librarian;
import com.bookzone.model.Person;
import com.bookzone.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

    @InjectMocks
    private RegistrationService registrationService;

    @Mock
    private Person librarian;

    @Mock
    private PersonRepository personRepository;

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
        validPassword = "SSS_helloWorld_12";
        librarian = new Librarian(validName, validUsername, validEmail, validPassword);
    }

    @Test
    void isEmailAddressAvailableReturnsTrue() {
        when(personRepository.findByEmail(validEmail)).thenReturn(Optional.empty());
        boolean result = registrationService.isEmailAddressAvailable(validEmail);
        assertTrue(result);
    }

    @Test
    void isEmailAddressAvailableReturnsFalse() {
        when(personRepository.findByEmail(validEmail)).thenReturn(Optional.of(librarian));
        boolean result = registrationService.isEmailAddressAvailable(validEmail);
        assertFalse(result);
        assertThrows(UnavailableEmailAddressException.class, () -> registrationService.registration(validName, validUsername, validEmail, validPassword));
    }

    @Test
    void isUsernameAvailableReturnsTrue() {
        when(personRepository.findByUsername(validUsername)).thenReturn(Optional.empty());
        boolean result = registrationService.isUsernameAvailable(validUsername);
        assertTrue(result);
    }

    @Test
    void isUsernameAvailableReturnsFalse() {
        when(personRepository.findByUsername(validUsername)).thenReturn(Optional.of(librarian));
        boolean result = registrationService.isUsernameAvailable(validUsername);
        assertFalse(result);
        assertThrows(UnavailableUsernameException.class, () -> registrationService.registration(validName, validUsername, validEmail, validPassword));
    }

    @Test
    void isValidEmailAddressReturnsFalse() {
        String invalidEmail = "hello_world.com";
        boolean result = registrationService.isValidEmailAddress(invalidEmail);
        assertFalse(result);
        assertThrows(InvalidEmailAddressException.class, () -> registrationService.registration(validName, validUsername, invalidEmail, validPassword));
    }

    @Test
    void isValidEmailAddressReturnsTrue() {
        boolean result = registrationService.isValidEmailAddress(validEmail);
        assertTrue(result);
    }

    @Test
    void isValidNameReturnsFalseAsItContainsLessThanTwoWords() {
        String invalidName = "John";
        boolean result = registrationService.isValidName(invalidName);
        assertFalse(result);
        assertThrows(InvalidNameException.class, () -> registrationService.registration(invalidName, validUsername, validEmail, validPassword));
    }

    @Test
    void isValidNameReturnsFalseDueToPresenceOfAtLeastOneSpecialCharacter() {
        String invalidName = "John W@yne";
        boolean result = registrationService.isValidName(invalidName);
        assertFalse(result);
        assertThrows(InvalidNameException.class, () -> registrationService.registration(invalidName, validUsername, validEmail, validPassword));
    }

    @Test
    void isValidNameReturnsFalseDueToAWordContainingLessThanThreeCharacters() {
        String invalidName = "Chris i Wayne";
        boolean result = registrationService.isValidName(invalidName);
        assertFalse(result);
        assertThrows(InvalidNameException.class, () -> registrationService.registration(invalidName, validUsername, validEmail, validPassword));
    }

    @Test
    void isValidNameReturnsFalseDueToAWordWithInsufficientNumberOfCharacters() {
        String invalidName = "Chris a Williams";
        boolean result = registrationService.isValidName(invalidName);
        assertFalse(result);
        assertThrows(InvalidNameException.class, () -> registrationService.registration(invalidName, validUsername, validEmail, validPassword));
    }

    @Test
    void isValidNameReturnsTrue() {
        boolean result = registrationService.isValidName(validName);
        assertTrue(result);
    }

    @Test
    void isValidPasswordReturnsTrue() {
        boolean result = registrationService.isValidPassword(validPassword);
        assertTrue(result);
    }

    @Test
    void isValidPasswordReturnsFalseDueToInsufficientNumberOfLowercaseLetters() {
        String invalidPassword = "HELLO_WoRLD12";
        boolean result = registrationService.isValidPassword(invalidPassword);
        assertFalse(result);
        assertThrows(InvalidPasswordException.class, () -> registrationService.registration(validName, validUsername, validEmail, invalidPassword));
    }

    @Test
    void isValidPasswordReturnsFalseDueToInsufficientNumberOfNumbers() {
        String invalidPassword = "HELLO_world1";
        boolean result = registrationService.isValidPassword(invalidPassword);
        assertFalse(result);
        assertThrows(InvalidPasswordException.class, () -> registrationService.registration(validName, validUsername, validEmail, invalidPassword));
    }

    @Test
    void isValidPasswordReturnsFalseDueToInsufficientUppercaseLetters() {
        String invalidPassword = "Hello_world11";
        boolean result = registrationService.isValidPassword(invalidPassword);
        assertFalse(result);
        assertThrows(InvalidPasswordException.class, () -> registrationService.registration(validName, validUsername, validEmail, invalidPassword));
    }

    @Test
    void isValidUsernameReturnsTrue() {
        boolean result = registrationService.isValidUsername(validUsername);
        assertTrue(result);
    }

    @Test
    void isValidUsernameReturnsFalseDueToHavingMoreThanOneWord() {
        String invalidUsername = "John Lee";
        boolean result = registrationService.isValidUsername(invalidUsername);
        assertFalse(result);
        assertThrows(InvalidUsernameException.class, () -> registrationService.registration(validName, invalidUsername, validEmail, validPassword));
    }

    @Test
    void isValidUsernameReturnsFalseDueToInsufficientLength() {
        String invalidUsername = "ada";
        boolean result = registrationService.isValidUsername(invalidUsername);
        assertFalse(result);
        assertThrows(InvalidUsernameException.class, () -> registrationService.registration(validName, invalidUsername, validEmail, validPassword));
    }

}
