package com.bookzone.repositoryTest;

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
import com.bookzone.repository.PersonRepository;

@ExtendWith(MockitoExtension.class)
class PersonRepositoryTest {

    @Mock
    private PersonRepository personRepository;

    @Test
    void testFindByEmail() {
        String email = "mike.lee@sgbookcollectors.com";
        Person librarian = new Librarian("Mike Lee", "mike_lee", email, "MMM22llla3");

        Optional<Librarian> optionalLibrarian = personRepository.findByEmail(email);

        verify(personRepository).findByEmail(email);
        assertEquals(librarian, optionalLibrarian.orElse(null));
    }
}
