package com.bookzone.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class LibrarianTest {

    @Mock
    Person librarian1;

    @Mock
    Person librarian2;

    @BeforeEach
    void setUp() {
        librarian1 = new Librarian("Lucy Tan", "lucy_tan", "lucy.tan@bookzone.com", "Lf21SafS@");
        librarian2 = new Librarian("Farah Ali", "farah_ali","farah.ali@bookzone.com", "M8@fz23");
    }

    @Test
    void testCheckNameReturnsCorrectName() {
        assertEquals("Farah Ali", librarian2.getName());
    }

    @Test
    void testCheckNameDoesNotReturnIncorrectName() {
        assertNotEquals(librarian1.getName(), librarian2.getName());
    }

    @Test
    void testCheckNameDoesNotReturnNullForExistingLibrarian() {
        assertNotEquals(null, librarian2.getName());
    }

    @Test
    void testCheckUsernameReturnsCorrectUsername() {
        assertEquals("farah_ali", librarian2.getUsername());
    }

    @Test
    void testCheckUsernameDoesNotReturnsIncorrectUsername() {
        assertNotEquals(librarian1.getUsername(), librarian2.getUsername());
    }

    @Test
    void testCheckUsernameDoesNotReturnNullForCorrectUsername() {
        assertNotEquals(null, librarian2.getUsername());
    }

    @Test
    void testCheckEmailReturnsCorrectEmail() {
        assertEquals("farah.ali@bookzone.com", librarian2.getEmail());
    }

    @Test
    void testCheckEmailDoesNotReturnIncorrectEmail() {
        assertNotEquals(librarian1.getEmail(), librarian2.getEmail());
    }

    @Test
    void testCheckEmailDoesNotReturnNullForExistingLibrarian() {
        assertNotEquals(null, librarian2.getEmail());
    }

    @Test
    void testCheckPasswordReturnsCorrectPassword() {
        assertEquals("Lf21SafS@", librarian1.getPassword());
    }

    @Test
    void testCheckPasswordDoesNotReturnIncorrectEmail() {
        assertNotEquals(librarian1.getPassword(), librarian2.getPassword());
    }

    @Test
    void testCheckPasswordDoesNotReturnNullForExistingLibrarian() {
        assertNotEquals(null, librarian1.getPassword());
    }

}
