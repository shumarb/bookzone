package com.bookzone.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

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
    void testFindByEmail() {
        String email = "mike.lee@sgbookcollectors.com";
        Person librarian = new Librarian("Mike Lee", "mike_lee", email, "MMM22llla3");

        Optional<Person> optionalLibrarian = personRepository.findByEmail(email);

        verify(personRepository).findByEmail(email);
        assertEquals(librarian, optionalLibrarian.orElse(null));
    }
}
