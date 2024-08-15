/**
 * Controller class that handles login-related requests.
 */

package com.bookzone.controller;

import com.bookzone.exceptions.UnsuccessfulLoginException;
import com.bookzone.model.Person;
import com.bookzone.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    /**
     * Logger to monitor operational flow and facilitate troubleshooting.
     */
    private static final Logger logger = LogManager.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    /**
     * Handles GET mapping related to displaying the Login page.
     *
     * @param   httpSession The HttpSession object.
     * @return  The name of view for the Login page.
     */
    @GetMapping("/login")
    public String showLogin(HttpSession httpSession) {
        logger.info("Currently at Login page.");
        return "login";
    }

    /**
     * Handles the POST mapping related to processing login attempts.
     *
     * @param email         The email address of the user.
     * @param password      The password of the user.
     * @param httpSession   The HttpSession to set the logged-in user.
     * @param model         The model where attributes can be added for the view
     * @return              Redirection to the Home page for a successful login, or login page
     *                      with an message explaining reason for unsuccessful login.
     */
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession httpSession,
                        Model model) {
        try {
            Person loggedInPerson = loginService.login(email, password);
            httpSession.setAttribute("loggedInPerson", loggedInPerson);
            logger.info("LoginControllerLogger: Successful login. Proceeding to Home page");
            return "redirect:/home";

        } catch (UnsuccessfulLoginException e) {
            logger.error("LoginControllerLogger: Unsuccessful login. Proceeding to Login page with error message displayed.");
            model.addAttribute("error", "Invalid email or password. Please try again.");
            return "login";

        } catch (Exception e) {
            logger.error("Unsuccessful login. Proceeding to Login page with error message displayed.");
            model.addAttribute("error", "Unexpected error occurred. Please try again later.");
            return "login";
        }
    }

}
