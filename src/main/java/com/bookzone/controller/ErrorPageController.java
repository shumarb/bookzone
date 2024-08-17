/**
 * Controller for handling requests for invalid URL access.
 */

package com.bookzone.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPageController implements ErrorController {

	/**
	 * Logger to monitor operational flow and facilitate troubleshooting.
	 */
	private static final Logger logger = LogManager.getLogger(ErrorPageController.class);
	
	/**
	 * Handles GET requests to the Error page.
	 * 
	 * @return View name of the Error page.
	 */
    @RequestMapping("/error")
    public String showError() {
    	logger.error("Invalid URL. Currently at error page");
        return "error";
    }

}
