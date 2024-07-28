/**
 * Service class that handles login-related requests.
 */

package com.bookzone.service;

import com.bookzone.model.Librarian;
import com.bookzone.model.Person;
import com.bookzone.repository.PersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class LoginService {

    /**
     * Logger to monitor operational flow and facilitate troubleshooting.
     */
    private static final Logger loginServiceLogger = LogManager.getLogger(LoginService.class);

    /**
     * Repository for performing CRUD operations on {@link Person} entities.
     */
    private final PersonRepository personRepository;

    /**
     * Constructs a {@code LoginController} with the specified {@code PersonRepository}
     */
    @Autowired
    public LoginService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Log the {@link Librarian} entity into the application.
     *
     * @param email 	The email address to check.
     * @param password  The password to check.
     * @return True if email and password matches that of an existing {@link Librarian} entity, false otherwise.
     */
    public boolean loginLibrarian(String email, String password) {
        Optional<Librarian> librarianOptional = this.personRepository.findByEmail(email);
        if (librarianOptional.isPresent()) {
            Librarian librarian = librarianOptional.get();
            if (librarian.getPassword().equals(password)) {
                loginServiceLogger.error("LoginServiceLogger: Email address provided exists and password provided is correct");
                return true;
            } else {
                loginServiceLogger.error("LoginServiceLogger: Email address provided exists but password provided is incorrect");
            }
        } else {
            loginServiceLogger.error("LoginServiceLogger: Email address provided does not exist");
        }
        return false;
    }
}
