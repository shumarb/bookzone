/**
 * Controller class that handles registration-related requests.
 */

package com.bookzone.controller;

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
    private static final Logger registrationControllerLogger = LogManager.getLogger(HomeController.class);

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
    public String goToRegistration() {
        registrationControllerLogger.info("RegistrationControllerLogger: Currently at Registration page");
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
    public String registerPerson(@RequestParam String name,
                                 @RequestParam String username,
                                 @RequestParam String email,
                                 @RequestParam String password,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {
        if (this.registrationService.isValidName(name) && this.registrationService.isValidEmailAddress(email) && this.registrationService.isValidPassword(password)) {
            this.registrationService.registerLibrarian(name, username, email, password);
            registrationControllerLogger.info("RegistrationControllerLogger: Librarian successfully registered, proceeding to Login page");
            redirectAttributes.addFlashAttribute("successfulRegistration", "Registration successful. Please log in.");
            return "redirect:/login";

        } else {

            if (!this.registrationService.isValidName(name)) {
                registrationControllerLogger.error("RegistrationControllerLogger: Unsuccessful registration due to invalid name, proceeding to Registration page");
                model.addAttribute("error", "Unsuccessful registration due to invalid name");

            } else if (!this.registrationService.isValidEmailAddress(email)) {
                registrationControllerLogger.error("RegistrationControllerLogger: Unsuccessful registration due to invalid email address, proceeding to Registration page");
                model.addAttribute("error", "Unsuccessful registration due to invalid email address");

            } else if (!this.registrationService.isValidPassword(password)) {
                registrationControllerLogger.error("RegistrationControllerLogger: Unsuccessful registration due to invalid password, proceeding to Registration page");
                model.addAttribute("error", "Unsuccessful registration due to invalid password");
            }
            return "registration";
        }
    }

}
