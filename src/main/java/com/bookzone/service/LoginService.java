/**
 * Service class that handles login-related requests.
 */

package com.bookzone.service;

import com.bookzone.exceptions.UnsuccessfulLoginException;
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
    private static final Logger logger = LogManager.getLogger(LoginService.class);

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
     * Attempts to log in a {@link Librarian} entity based on the specified email address and password.
     * This method checks the provided email address against registered persons.
     * If the email address exists, it then verified that the specified password matches the stored password.
     * If either check fails, an {@link UnsuccessfulLoginException} is thrown.
     *
     * @param email 	The email address of the {@link Person} entity attempting to log in.
     * @param password  The password of the {@link Person} entity attempting to log in.
     *
     * @thrown          UnsuccessfulLoginException If the email address is not found,
     *                  or if the password does not match the stored password for the given email address.
     */
    public Person login(String email, String password) throws UnsuccessfulLoginException {
        logger.info("Login attempt | Email address: {}, Password: {}", email, password);
        Optional<Person> personOptional = personRepository.findByEmail(email);
        if (personOptional.isEmpty()) {
            logger.error("Unsuccessful login due to incorrect email address: {}", email);
            throw new UnsuccessfulLoginException();
        }
        Person person = personOptional.get();
        if (!person.getPassword().equals(password)) {
            logger.error("Unsuccessful login due to incorrect password: {}", password);
            throw new UnsuccessfulLoginException();
        }
        logger.info("Successful login | {}", person.toString());
        return person;
    }

}
