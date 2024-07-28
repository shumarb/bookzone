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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String goToIndex() {
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
	 * Goes to the Login page
	 * 
	 * @return name of the Login page
	 */
	@GetMapping("/login")
	public String goToLogin() {
		librarianControllerLogger.info("LibrarianControllerLogger: Currently at Login page");
		return "login";
	}
	
	/**
	 * Manages a Librarian's login process
	 * 
	 * @param email: email address of the Librarian
	 * @param password: password of the Librarian
	 * @param model: The model where attributes can be added for the view
	 * @return Redirection to the Home page for a successful login, or back to the login page
	 * with an error message displayed for unsuccessful login
	 */
	@PostMapping("/login")
	public String loginLibrarian(@RequestParam String email, @RequestParam String password, Model model) {
	    boolean doesLibrarianExist = this.registrationService.loginLibrarian(email, password);
	    if (doesLibrarianExist) {
	        librarianControllerLogger.info("LibrarianControllerLogger: Librarian successfully logged in, proceeding to Home page");
	        return "redirect:/home";
	    } else {
	        librarianControllerLogger.error("LibrarianControllerLogger: Librarian unsuccessful in logging in, proceeding to Login page");
	        model.addAttribute("error", "Invalid email or password. Please try again.");
	        return "login";
	    }
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
