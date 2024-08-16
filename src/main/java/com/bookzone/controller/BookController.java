/**
 * Handles requests for adding, editing, and deleting books.
 * 
 * @author Sheikh Umar
 */

package com.bookzone.controller;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.bookzone.model.Book;
import com.bookzone.service.BookService;

@Controller
public class BookController {

	/**
	 * Logger to monitor operational flow and facilitate troubleshooting.
	 */
	private static final Logger logger = LogManager.getLogger(BookController.class);
	
	/**
	 * Service for managing {@link Book} entities.
	 */
	@Autowired
	private BookService bookService;
	
	/**
	 * Handles the GET request for the Add Book page.
	 * 
	 * @return View name of the Add page.
	 */
	@GetMapping("/add")
	public String showAdd() {
		logger.info("Currently at Add page.");
		return "add";
	}
	
	/**
	 * Handles the GET request to retrieve all books in the catalogue.
	 * 
	 * @return 	A ModelAndView object containing the view name of the Catalogue page and list of books.
	 */
	@GetMapping("/catalogue")
	public ModelAndView getAllBooks() {
		List<Book> list = bookService.getAllBooks();
		logger.info("Displaying all books in the catalogue");
		return new ModelAndView("catalogue", "book", list);
	}
	
	/**
	 * Handles the POST request to add a new {@link Book} to the catalogue.
	 *
	 * @param book 	The {@link Book} entity to add.
	 * @return 		Redirection to view name of Catalogue page.
	 */
	@PostMapping("/save")
	public String addBook(@ModelAttribute Book book) {
		logger.info("Adding to catalogue: {}", book.toString());
		this.bookService.saveBook(book);
		return "redirect:/catalogue";
	}
	
	/**
	 * Handles the PUT request to update a {@link Book} entity.
	 * 
	 * @param id 		The id of the {@link Book} entity to edit.
	 * @param model 	The model to add the {@link Book} entity to.
	 * @return 			The view name of the Edit page.
	 */
	@PutMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") long id, Model model) {
		Book book = bookService.getBookById(id);
		logger.info("Editing book: {}", book.toString());
		model.addAttribute("book", book);
		return "edit";
	}
	
	/**
	 * Handles the DELETE request to remove a {@link Book} entity from the catalogue.
	 * 
	 * @param id 	The id of a {@link Book} entity to be deleted.
	 * @return 		Redirection to the view name of the Catalogue page.
	 */
	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id") long id) {
		Book book = bookService.getBookById(id);
		bookService.deleteBook(id);
		logger.info("Deleting from catalogue: {}", book.toString());
		return "redirect:/catalogue";
	}
	
}
