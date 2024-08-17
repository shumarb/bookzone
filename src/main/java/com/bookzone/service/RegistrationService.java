/**
 * Service class for handling registration of {@link Person} entities.
 */

package com.bookzone.service;

import com.bookzone.exceptions.*;
import com.bookzone.model.Person;
import com.bookzone.repository.PersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookzone.model.Librarian;

@Service
public class RegistrationService {

	/**
	 * Logger to monitor operational flow and facilitate troubleshooting.
	 */
	private static final Logger logger = LogManager.getLogger(RegistrationService.class);

	/**
	 * Repository for performing CRUD operation on {@link Person} entities.
	 */
	@Autowired
	private PersonRepository personRepository;

	/**
	 * Constructs a {@code RegistrationService} with the specified {@code PersonRepository}.
	 */
	@Autowired
	public RegistrationService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	/**
	 * Registers a {@link Librarian} entity.
	 *
	 * @param name		The name of the {@link Librarian} entity.
	 * @param username  The username of the {@link Librarian} entity.
	 * @param email		The email address of the {@link Librarian} entity.
	 * @param password	The password of the {@link Librarian} entity.
	 */
	public Person registration(String name, String username, String email, String password) throws 	InvalidNameException,
																								 	InvalidUsernameException,
																								 	InvalidEmailAddressException,
																								 	InvalidPasswordException,
																								 	UnavailableUsernameException,
																								 	UnavailableEmailAddressException {

		if (!isValidName(name)) {
			throw new InvalidNameException();
		}

		if (!isValidUsername(username)) {
			throw new InvalidUsernameException();
		}

		if (!isValidEmailAddress(email)) {
			throw new InvalidEmailAddressException();
		}

		if (!isValidPassword(password)) {
			throw new InvalidPasswordException();
		}

		if (!isUsernameAvailable(username)) {
			throw new UnavailableUsernameException();
		}

		if (!isEmailAddressAvailable(email)) {
			throw new UnavailableEmailAddressException();
		}

		personRepository.save(new Librarian(name, username, email, password));
		Person registeredPerson = null;
		if (personRepository.findByEmail(email).isPresent()) {
			registeredPerson = personRepository.findByEmail(email).get();
			logger.info("Successful registration: {}", registeredPerson.toString());
		}
		return registeredPerson;
	}

	/**
	 * Checks if an email address is available for registration.
	 *
	 * @param email The email address to check.
	 * @return True if the email address is not unregistered, false otherwise.
	 */
	private boolean isEmailAddressAvailable(String email) {
		boolean result = personRepository.findByEmail(email).isEmpty();
		if (result) {
			logger.info("Email address is available for registration: {}", email);
		} else {
			logger.info("Email address is unavailable for registration: {}", email);
		}
		return result;
	}

	/**
	 * Checks if a username is available for registration.
	 *
	 * @param username The username to check.
	 * @return True is username is unregistered, false otherwise.
	 */
	private boolean isUsernameAvailable(String username) {
		return personRepository.findByUsername(username).isEmpty();
	}

	/**
	 * Checks if a username is valid.
	 *
	 * @param username The username to check.
	 * @return True if username comprises a single word with at least 5 characters, false otherwise.
	 */
	private boolean isValidUsername(String username) {
		String[] words = username.split(" ");
		if (words.length != 1) {
			return false;
		}
		return username.length() >= 5;
	}

	/**
	 * Checks if an email address is valid.
	 * 
	 * @param email The Email address to check.
	 * @return True if email address is valid, false otherwise.
	 */
	public boolean isValidEmailAddress(String email) {
		if (email.endsWith("@sgbookcollectors.com")) {
			logger.info("Valid email address: {}", email);
			return true;
		}
		logger.info("Invalid email address: {}", email);
		return false;
	}
	
	/**
	 * Checks if the name is valid.
	 * 
	 * @param name The name to check.
	 * @return True if name has at least 2 words, and each word has at least 3 characters.
	 */
	public boolean isValidName(String name) {
		String[] nameValues = name.split(" ");
		// 1. Invalid if name has less than 2 words
		if (nameValues.length < 2) {
			logger.error("Name has less than 2 words: {}", name);
			return false;
		}
		
		// 2. Invalid if each word has less than 3 characters
        for (String nameValue : nameValues) {
            if (nameValue.length() < 3) {
                logger.info("At least 1 word in the name has less than 3 characters: {}", name);
                return false;
            }
        }
		
		// 3. Name has at least 2 words, and each word has at least 3 characters
		// Check if every character in the word is a letter
		// Invalid if there exists at least 1 character that is not a letter
        for (String word : nameValues) {
            if (word != null && !word.matches("[a-zA-Z]+")) {
                logger.error("There is at least 1 character detected in the name: {}", name);
                return false;
            }
        }
		
		logger.info("Valid name: {}", name);
		return true;
	}
	
	/**
	 * Checks if password has:
	 * 1.	At least 8 characters.
	 * 2.	At least 3 uppercase letters.
	 * 3. 	At least 3 lowercase letters.
	 * 4. 	At least 2 numbers.
	 * 
	 * @param 	password The password to check
	 * @return 	True if password satisfies all requirements stated above, false otherwise.
	 */
	public boolean isValidPassword(String password) {
		if (password.length() < 8) {
			logger.info("Invalid password: Password length is less than 8 characters: {}", password);
			return false;
		}

		int numberOfUpperCaseLetters = 0;
	    int numberOfLowerCaseLetters = 0;
	    int numberOfNumbers = 0;
	    
	    for (int i = 0; i < password.length(); i++) {
	    	char currentChar = password.charAt(i);
	    	if (Character.isUpperCase(currentChar)) {
	    		numberOfUpperCaseLetters++;
	    	}
	    	if (Character.isLowerCase(currentChar)) {
	    		numberOfLowerCaseLetters++;
	    	}
	    	if (Character.isDigit(currentChar)) {
	    		numberOfNumbers++;
	    	}
	    }

		if (numberOfUpperCaseLetters < 3) {
			logger.info("Invalid password: Password has less than 3 uppercase letters: {}", password);
			return false;

		} else if (numberOfLowerCaseLetters < 3) {
			logger.info("Invalid password: Password has less than 3 lowercase letters: {}", password);
			return false;

		} else if (numberOfNumbers < 2) {
			logger.info("Invalid password: Password has less than 2 numbers: {}", password);
			return false;

		} else {
			logger.info("Valid password: {}", password);
			return true;
		}
	}

}
