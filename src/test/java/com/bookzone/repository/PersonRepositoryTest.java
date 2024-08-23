package com.bookzone.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.bookzone.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bookzone.model.Librarian;

@ExtendWith(MockitoExtension.class)
class PersonRepositoryTest {

    @Mock
    private PersonRepository personRepository;

    @Test
    void returnsCorrectLibrarianWhenEmailIsFound() {
        // Arrange
        String email = "mike.lee@sgbookcollectors.com";
        Person librarian = new Librarian("Mike Lee", "mike_lee", email, "HELLoWorld123");
        when(personRepository.findByEmail(email)).thenReturn(Optional.of(librarian));

        // Act
        Optional<Person> optionalLibrarian = personRepository.findByEmail(email);

        // Assert
        verify(personRepository).findByEmail(email);
        assertEquals(librarian, optionalLibrarian.orElse(null));
    }

}
