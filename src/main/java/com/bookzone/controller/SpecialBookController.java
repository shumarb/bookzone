/**
 * The SpecialBookController class handles incoming requests, processes these requests, 
 * and provides the appropriate responses for deleting a Book from a list of Special Books.
 * 
 * @author Sheikh Umar
 */

package com.bookzone.controller;

import com.bookzone.model.SpecialBook;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookzone.service.SpecialBookService;

import java.util.List;

@Controller
public class SpecialBookController {

	/**
	 * Logger used for logging messages of the SpecialBookController class
	 */
	private static final Logger specialBookControllerLogger = LogManager.getLogger(SpecialBookController.class);
	
	/**
	 * SpecialBookService to manage the SpecialBook entity
	 */
	@Autowired
	private SpecialBookService specialBookService;

	/**
	 * Gets all Special books from the catalogue and adds it to the catalogue for display
	 *
	 * @param model: The model in which the SpecialBook will be added
	 * @return name of the SpecialBook page
	 */
	@GetMapping("/specials")
	public String getSpecialBooks(Model model) {
		List<SpecialBook> list = specialBookService.getAllSpecialBooks();
		model.addAttribute("book", list);
		specialBookControllerLogger.info("Displaying all special books.");
		return "specials";
	}

	/**
	 * Deletes a SpecialBook by its id
	 * 
	 * @param id of the SpecialBook to be deleted
	 * @return redirection to Specials webpage
	 * that displays all remaining SpecialBooks
	 */
	@RequestMapping("/deleteSpecialBook/{id}")
	public String deleteSpecialBook(@PathVariable("id") long id) {
		this.specialBookService.deleteSpecialBookById(id);
		specialBookControllerLogger.info("Deleting a SpecialBook from the SpecialBook list.");
		return "redirect:/specials";
	}
	
}
