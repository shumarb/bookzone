/**
 * Controller class that handles requests for the Home page.
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
	 * Logger to monitor operational flow and facilitate troubleshooting.
	 */
	private static final Logger logger = LogManager.getLogger(HomeController.class);
	
	/**
	 * Handles the GET request for the Home page.
	 * Retrieves the logged-in {@link Person}'s details from the session and adds them to the model.
	 *
	 * @param httpSession	The HTTP session containing the logged-in {@link Person}'s information.
	 * @param model			The model to add attributes for the view.
	 * @return 				The name of the view for the Home page.
	 */
	@GetMapping("/home")
	public String showHome(HttpSession httpSession, Model model) {
		try {
			Person loggedInPerson = (Person) httpSession.getAttribute("loggedInPerson");
			httpSession.setAttribute("loggedInPerson", loggedInPerson);
			logger.info("Currently at Home page. Accessed by {}", loggedInPerson.toString());
			model.addAttribute("loggedInPerson", loggedInPerson);
			return "home";
		} catch (Exception e) {
			logger.error("No logged-in user found in the session.");
			return "redirect:/login";
		}
	}

	/**
	 * Handles the logout process for the logged-in {@link Person}.
	 * Invalidates the current session, logs the logout event,
	 * and redirects to the index page with a successful logout message displayed.
	 *
	 * @param httpSession 			The HTTP session to be invalidated.
	 * @param redirectAttributes   	The attributes to add for redirection with flash message.
	 * @return 						A redirect to the Index page with a successful logout message.
	 */
	@PostMapping("/logout")
	public String logout(HttpSession httpSession, RedirectAttributes redirectAttributes) {
		try {
			Person loggedInPerson = (Person) httpSession.getAttribute("loggedInPerson");
			logger.info("Successful logout | {}", loggedInPerson.toString());
			httpSession.invalidate();
			redirectAttributes.addFlashAttribute("successfulLogout", "You have successfully logged out.");
			return "redirect:/";

		} catch (Exception e){
			logger.error("Unable to logout.");
			return "home";
		}
	}
	
}
