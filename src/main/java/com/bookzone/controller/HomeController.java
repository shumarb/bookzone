/**
 * Controller class that handles requests related to the Home page.
 *
 */

package com.bookzone.controller;

import com.bookzone.model.Person;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	public String showHome(HttpSession httpSession, Model model) {
		Person loggedInPerson = (Person) httpSession.getAttribute("loggedInPerson");
		homeControllerLogger.info("Currently at Home page. Accessed by {}", loggedInPerson.toString());
		model.addAttribute("loggedInPerson", loggedInPerson);
		return "home";
	}

	/**
	 * Logs out the Librarian
	 * 
	 * @return redirection to the Index page
	 */
	@PostMapping("/logout")
	public String logout(HttpSession httpSession, RedirectAttributes redirectAttributes) {
		Person loggedInPerson = (Person) httpSession.getAttribute("loggedInPerson");
		httpSession.invalidate();
		homeControllerLogger.info("Successful logout | {}", loggedInPerson.toString());
		redirectAttributes.addFlashAttribute("successfulLogout", "You have successfully logged out.");
		return "redirect:/";
	}
	
}
