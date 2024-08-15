/**
 * Controller class that handles requests related to the Home page.
 *
 */

package com.bookzone.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

	/**
	 * Logger used for logging messages of the LibrarianController
	 */
	private static final Logger homeControllerLogger = LogManager.getLogger(HomeController.class);
	
	/**
	 * Goes to the Home page
	 * 
	 * @return name of the Home page
	 */
	@GetMapping("/home")
	public String showHome() {
		homeControllerLogger.info("Currently at Home page");
		return "home";
	}

	/**
	 * Logs out the Librarian
	 * 
	 * @return redirection to the Index page
	 */
	@GetMapping("/logout")
	public String logout(RedirectAttributes redirectAttributes) {
		homeControllerLogger.info("Librarian logged out, proceeding to Index page");
		redirectAttributes.addFlashAttribute("successfulLogout", "You have successfully logged out.");
		return "redirect:/";
	}
	
}
