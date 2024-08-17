/**
 * Controller class that handles requests for the Registration page.
 */

package com.bookzone.controller;

import com.bookzone.exceptions.InvalidEmailAddressException;
import com.bookzone.exceptions.InvalidNameException;
import com.bookzone.exceptions.InvalidPasswordException;
import com.bookzone.exceptions.InvalidUsernameException;
import com.bookzone.exceptions.UnavailableUsernameException;
import com.bookzone.exceptions.UnavailableEmailAddressException;
import com.bookzone.model.Person;
import com.bookzone.service.RegistrationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class RegistrationController {

    /**
     * Logger used for logging messages of the LibrarianController
     */
    private static final Logger logger = LogManager.getLogger(HomeController.class);

    /**
     * Service to handle registration operations for {@link Person} entities.
     */
    @Autowired
    private RegistrationService registrationService;

    /**
     * Constructs a {@code RegistrationController} with the specified {@code RegistrationService}.
     *
     * @param registrationService The service used to manage registrations.
     */
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    /**
     * Handles the GET request for the Registration page.
     *
     * @return A string indicating the view name of the Registration page.
     */
    @GetMapping("/registration")
    public String showRegistration() {
        logger.info("Currently at Registration page");
        return "registration";
    }

    /**
     * Manages the POST request related to the {@link Person} entity's registration.
     *
     * @param name               The name of the {@link Person} entity.
     * @param username           The username of the {@link Person} entity.
     * @param email              The email address of the {@link Person} entity.
     * @param password           The password of the {@link Person} entity.
     * @param model              the model to which attributes can be added for the view, used to pass error messages.
     * @param redirectAttributes The redirect attributes to add flash attributes, used for passing success messages across redirects.
     * @return                   A string indicating the view name to be rendered.
     *                           Redirects to the login page if registration is successful,
     *                           or returns the registration page with error messages if validation fails.
     */
    @PostMapping("/registration")
    public String registration(@RequestParam String name,
                               @RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String password,
                               Model model,
                               RedirectAttributes redirectAttributes) throws InvalidNameException,
                                                                             InvalidUsernameException,
                                                                             InvalidEmailAddressException,
                                                                             InvalidPasswordException,
                                                                             UnavailableUsernameException,
                                                                             UnavailableEmailAddressException {
        logger.info("Registration attempt | Name: {}, Username: {}, Email Address: {}, Password: {}", name, username, email, password);
        try {
            Person registeredPerson = registrationService.registration(name, username, email, password);
            logger.info("Successful registration: {}", registeredPerson.toString());
            redirectAttributes.addFlashAttribute("successfulRegistration", "Registration successful. Please log in.");
            return "redirect:/login";

        } catch (InvalidNameException e) {
            logger.error("Unsuccessful registration due to invalid name: {}", name);
            model.addAttribute("error", "Unsuccessful registration due to invalid name");

        } catch (InvalidEmailAddressException e) {
            logger.error("Unsuccessful registration due to invalid email address: {}", email);
            model.addAttribute("error", "Unsuccessful registration due to invalid email address");

        } catch (InvalidPasswordException e) {
            logger.error("Unsuccessful registration due to invalid password: {}", password);
            model.addAttribute("error", "Unsuccessful registration due to invalid password");

        } catch (InvalidUsernameException e) {
            logger.error("Unsuccessful registration due to invalid username, proceeding to Registration page with error message displayed.");
            model.addAttribute("error", "Unsuccessful registration due to invalid password");

        } catch (UnavailableUsernameException e) {
            logger.error("Unsuccessful registration due to unavailable username: {}", username);
            model.addAttribute("error", "Username entered is unavailable. Please enter another username.");

        } catch (UnavailableEmailAddressException e) {
            logger.error("Unsuccessful registration due to unavailable email address: {}", email);
            model.addAttribute("error", "Email address entered is unavailable. Please enter another email address.");

        } catch (Exception e) {
            logger.error("Unsuccessful registration due to unexpected error, proceeding to Registration page with error message displayed.");
            model.addAttribute("error", "Unexpected error occurred. Please try again later.");
        }

        return "registration";
    }

}
