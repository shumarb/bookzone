/**
 * Service class for managing {@link Book} entities.
 */

package com.bookzone.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookzone.model.Book;
import com.bookzone.repository.BookRepository;

@Service
public class BookService {

	/**
	 * Logger to monitor operational flow and facilitate troubleshooting.
	 */
	private static final Logger logger = LogManager.getLogger(BookService.class);

	/**
	 * {@link BookRepository} for managing {@link Book} entities.
	 */
	@Autowired
	private BookRepository bookRepository;
	
	/**
	 * Saves a {@link Book} to the catalogue.
	 * 
	 * @param book The {@link Book} to save.
	 */
	public void saveBook(Book book) {
		bookRepository.save(book);
		logger.info("Adding: {}", book);
	}
	
	/**
	 * Retrieves all {@link Book} entities in the catalogue.
	 * 
	 * @return A {@link List} of all {@link Book} entities.
	 */
	public List<Book> getAllBooks() {
		logger.info("Retrieving all books in the catalogue.");
		return bookRepository.findAll();
	}
	
	/**
	 * Retrieves a {@link Book} by its identifier.
	 * 
	 * @param 	id The identifier of the {@link Book}.
	 * @return 	The {@link Book} with the given identifier, or null if not found.
	 */
	public Book getBookById(long id) {
		Book book = null;
		if (bookRepository.findById(id).isPresent()) {
			book = bookRepository.findById(id).get();
			logger.info("Retrieving {}", book.toString());
		}
		return book;
	}
	
	/**
	 * Deletes a {@link Book} by its identifier.
	 * 
	 * @param id The identifier of the {@link Book}.
	 */
	public void deleteBook(long id) {
		Book book;
		if (bookRepository.findById(id).isPresent()) {
			book = bookRepository.findById(id).get();
			logger.info("Deleting {}", book.toString());
		}
		bookRepository.deleteById(id);
	}
	
}
