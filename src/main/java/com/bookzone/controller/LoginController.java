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
    private static final Logger loginControllerLogger = LogManager.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    /**
     * Goes to the Login page.
     *
     * @param httpSession The HttpSession object.
     * @return name of view for the Login page.
     */
    @GetMapping("/login")
    public String showLogin(HttpSession httpSession) {
        loginControllerLogger.info("LoginControllerLogger: Currently at Login page.");
        return "login";
    }

    /**
     * Manages a Librarian's login process,
     *
     * @param email     The email address of the {@link Person} entity attempting to log in.
     * @param password  The password of the {@link Person} entity attempting to log in.
     * @param model     The model where attributes can be added for the view
     * @return          Redirection to the Home page for a successful login, or back to the login page
     *                  with an error message displayed for unsuccessful login
     */
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        try {
            loginService.login(email, password);
            loginControllerLogger.info("LoginControllerLogger: Successful login. Proceeding to Home page");
            return "redirect:/home";

        } catch (UnsuccessfulLoginException e) {
            loginControllerLogger.error("LoginControllerLogger: Unsuccessful login. Proceeding to Login page with error message displayed.");
            model.addAttribute("error", "Invalid email or password. Please try again.");
            return "login";

        } catch (Exception e) {
            loginControllerLogger.error("Unsuccessful login. Proceeding to Login page with error message displayed.");
            model.addAttribute("error", "Unexpected error occurred. Please try again later.");
            return "login";
        }
    }

}
