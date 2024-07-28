/**
 * The LibrarianController class handles incoming requests, processes these requests, 
 * and provides the appropriate webpages for the Librarian.
 * 
 * @author Sheikh Umar
 */

package com.bookzone.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.bookzone.service.RegistrationService;

@Controller
public class LibrarianController {

	/**
	 * Logger used for logging messages of the LibrarianController
	 */
	private static final Logger librarianControllerLogger = LogManager.getLogger(LibrarianController.class);
	
	/**
	 * RegistrationService to manage the Librarians
	 */
	@Autowired
	private RegistrationService registrationService;
	
	/**
	 * Goes to the Index page
	 * 
	 * @return name of the Index page
	 */
	@GetMapping("/")
	public String showIndex() {
		librarianControllerLogger.info("LibrarianControllerLogger: Currently at Index page");
		return "index";
	}
	
	/**
	 * Goes to the Home page
	 * 
	 * @return name of the Home page
	 */
	@GetMapping("/home")
	public String goToHome() {
		librarianControllerLogger.info("LibrarianControllerLogger: Currently at Home page");
		return "home";
	}

	/**
	 * Logs out the Librarian
	 * 
	 * @return redirection to the Index page
	 */
	@GetMapping("/logout")
	public String logoutLibrarian() {
		librarianControllerLogger.info("LibrarianControllerLogger: Librarian logged out, proceeding to Index page");
		return "redirect:/";
	}
	
}
