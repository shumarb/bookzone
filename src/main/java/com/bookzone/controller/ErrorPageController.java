/**
 * The ErrorController class handles situations where the URL cannot be processed by any controller
 * by loading the Error page.
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
	 * Logger used for logging messages of the MyErrorController
	 */
	private static final Logger logger = LogManager.getLogger(ErrorPageController.class);
	
	/**
	 * Handles error requests by returning name of a page
	 * displaying an error message and links for login and registration
	 * 
	 * @return name of a page that displays an error message
	 * and links for login and registration
	 */
    @RequestMapping("/error")
    public String goesToError() {
    	logger.error("Invalid URL. Currently at error page");
        return "error";
    }

}
