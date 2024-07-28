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
    void setUp() throws Exception {
        librarian1 = new Librarian("Lucy Tan", "lucy_tan", "lucy.tan@bookzone.com", "Lf21SafS@");
        librarian2 = new Librarian("Farah Ali", "farah_ali","farah.ali@bookzone.com", "M8@fz23");
    }

    @Test
    void test_checkName_returnsCorrectName() {
        assertEquals("Farah Ali", librarian2.getName());
    }

    @Test
    void test_checkName_doesNotReturnIncorrectName() {
        assertNotEquals(librarian1.getName(), librarian2.getName());
    }

    @Test
    void test_checkName_doesNotReturnNullForExistingLibrarian() {
        assertNotEquals(null, librarian2.getName());
    }

    @Test
    void test_checkUsername_returnsCorrectUsername() {
        assertEquals("farah_ali", librarian2.getUsername());
    }

    @Test
    void test_checkUsername_doesNotReturnsIncorrectUsername() {
        assertNotEquals(librarian1.getUsername(), librarian2.getUsername());
    }

    @Test
    void test_checkUsername_doesNotReturnNullForCorrectUsername() {
        assertNotEquals(null, librarian2.getUsername());
    }

    @Test
    void test_checkEmail_returnsCorrectEmail() {
        assertEquals("farah.ali@bookzone.com", librarian2.getEmail());
    }

    @Test
    void test_checkEmail_doesNotReturnIncorrectEmail() {
        assertNotEquals(librarian1.getEmail(), librarian2.getEmail());
    }

    @Test
    void test_checkEmail_doesNotReturnNullForExistingLibrarian() {
        assertNotEquals(null, librarian2.getEmail());
    }

    @Test
    void test_checkPassword_returnsCorrectPassword() {
        assertEquals("Lf21SafS@", librarian1.getPassword());
    }

    @Test
    void test_checkPassword_doesNotReturnIncorrectEmail() {
        assertNotEquals(librarian1.getPassword(), librarian2.getPassword());
    }

    @Test
    void test_checkPassword_doesNotReturnNullForExistingLibrarian() {
        assertNotEquals(null, librarian1.getPassword());
    }

}
