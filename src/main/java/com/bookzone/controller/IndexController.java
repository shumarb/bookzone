/**
 * Controller class that handles requests for the Index page.
 */

package com.bookzone.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    /**
     * Logger to monitor operational flow and facilitate troubleshooting.
     */
    private static final Logger logger = LogManager.getLogger(IndexController.class);

    /**
     * Handles the GET request for the Index page.
     *
     * @return The name of the view for the Index page.
     */
    @GetMapping("/")
    public String showIndex() {
        logger.info("Currently at Index page.");
        return "index";
    }

}
