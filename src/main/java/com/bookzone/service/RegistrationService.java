/**
 * Service class for handling registration of {@link Person} entities.
 */

package com.bookzone.service;

import java.util.Optional;

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
	private static final Logger registrationServiceLogger = LogManager.getLogger(RegistrationService.class);

	/**
	 * Repository for performing CRUD operation on {@link Person} entities..
	 */
	@Autowired
	private PersonRepository personRepository;
	
	/**
	 * Registers a {@link Librarian} entity.
	 *
	 * @param name		The name of the {@link Librarian} entity.
	 * @param username  The username of the {@link Librarian} entity.
	 * @param email		The email address of the {@link Librarian} entity.
	 * @param password	The password of the {@link Librarian} entity.
	 */
	public void registerLibrarian(String name,
								  String username,
								  String email,
								  String password) {
		this.personRepository.save(new Librarian(name, username, email, password));
		Person librarian = personRepository.findByEmail(email).get();
		registrationServiceLogger.info("RegistrationServiceLogger: Librarian registered: {}", librarian.toString());
	}
	
	/**
	 * Checks if an email address is valid.
	 * 
	 * @param email The Email address to check.
	 * @return True if email address is valid, false otherwise
	 */
	public boolean isValidEmailAddress(String email) {
		Optional<Librarian> librarianOptional = this.personRepository.findByEmail(email);
		
		// 1. Check if email address provided exists
		if (librarianOptional.isPresent()) {
			registrationServiceLogger.error("RegistrationServiceLogger: Email address provided is unavailable");
			return false;
		}
		
		// 2. Check if email address provided ends with @sgbookcollectors.com
		if (isEndWithCompanyEmailAddress(email)) {
			registrationServiceLogger.info("RegistrationServiceLogger: Email address provided is valid");
			return true;
		}
		registrationServiceLogger.error("RegistrationServiceLogger: Email address provided is invalid");
		return false;
	}
	
	/**
	 * Checks if password has at least 8 characters.
	 * 
	 * @param 	password The password to check.
	 * @return 	True if password has at least 8 characters, false otherwise.
	 */
	public boolean hasAtLeastEightCharacters(String password) {
		return password.length() >= 8;
	}
	
	/**
	 * Checks if email address entered is valid.
	 * 
	 * @param email The email address to check.
	 * @return True if email address ends with @sgbookcollectors.com, false otherwise.
	 */
	public boolean isEndWithCompanyEmailAddress(String email) {
		if (email.endsWith("@sgbookcollectors.com")) {
			registrationServiceLogger.info("LibrarianServiceLogger: Email address provided ends with @sgbookcollectors.com");
			return true;
		}
		registrationServiceLogger.error("LibrarianServiceLogger: Email address provided does not end with @sgbookcollectors.com");
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
			registrationServiceLogger.error("LibrarianServiceLogger: Name has less than 2 words");
			return false;
		}
		
		// 2. Invalid if each word has less than 3 characters
        for (String nameValue : nameValues) {
            if (nameValue.length() < 3) {
                registrationServiceLogger.info("LibrarianServiceLogger: At least 1 word in the name has less than 3 characters");
                return false;
            }
        }
		
		// 3. Name has at least 2 words, and each word has at least 3 characters
		// Check if every character in the word is a letter
		// Invalid if there exists at least 1 character that is not a letter
        for (String word : nameValues) {
            if (word != null && !word.matches("[a-zA-Z]+")) {
                registrationServiceLogger.error("LibrarianServiceLogger: There is at least 1 character detected in the name");
                return false;
            }
        }
		
		registrationServiceLogger.info("LibrarianServiceLogger: Valid name");
		return true;
	}
	
	/**
	 * Checks if password has at least 3 uppercase letters,
	 * at least 3 lowercase letters, and at least 2 numbers
	 * 
	 * @param 	password The password to check
	 * @return True if password has at least 3 uppercase letters, at least 3 lowercase letters,
	 * and at least 2 numbers valid password, false otherwise.
	 */
	public boolean hasSufficientUpperCaseAndLowerCaseAndNumbers(String password) {
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
	    
	    if (numberOfUpperCaseLetters >= 3 && numberOfLowerCaseLetters >= 3 && numberOfNumbers >= 2) {
	    	registrationServiceLogger.info("LibrarianServiceLogger: Valid password: Password has at least 8 characters and contains at least 3 are uppercase letters, at least 3 lowercase letters, and at least 2 numbers");
	    } else {
	    	registrationServiceLogger.info("LibrarianServiceLogger: Invalid password: Password has at least 8 characters and at least one of these situations: less than 3 uppercase letters, less than 3 lowercase letters, and less than 2 numbers");
	    }
	    return numberOfUpperCaseLetters >= 3 && numberOfLowerCaseLetters >= 3 && numberOfNumbers >= 2;
	}
	
	/**
	 * Checks if password provided is valid.
	 * 
	 * @param password The password to check.
	 * @return True if valid password, false otherwise.
	 */
	public boolean isValidPassword(String password) {
	    return hasAtLeastEightCharacters(password) && hasSufficientUpperCaseAndLowerCaseAndNumbers(password);
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
				registrationServiceLogger.error("LibrarianServiceLogger: Email address provided exists and password provided is correct");
				return true;
			} else {
				registrationServiceLogger.error("LibrarianServiceLogger: Email address provided exists but password provided is incorrect");
			}
		} else {
			registrationServiceLogger.error("LibrarianServiceLogger: Email address provided does not exist");
		}
		return false;
	}

}
