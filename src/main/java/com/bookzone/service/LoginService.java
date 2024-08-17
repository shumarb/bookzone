/**
 * Service class for handling login of {@link Person} entities.
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
     * Constructs a {@code LoginService} with the specified {@code PersonRepository}.
     */
    @Autowired
    public LoginService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Authenticates a {@link Librarian} entity based on the specified email address and password.
     *
     * @param email 	The email address of the {@link Person} entity attempting to log in.
     * @param password  The password of the {@link Person} entity attempting to log in.
     *
     * @thrown          UnsuccessfulLoginException If the email address is not found or incorrect password.
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
