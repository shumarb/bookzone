/**
 * BookService is a Service class to manage the Book entity
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
	 * BookRepository to manage the Book entity
	 */
	@Autowired
	private BookRepository bookRepository;
	
	/**
	 * Saves a Book to the catalogue.
	 * 
	 * @param book to saveBook to the catalogue.
	 */
	public void saveBook(Book book) {
		bookRepository.save(book);
		logger.info("Added to catalogue: {}", book);
	}
	
	/**
	 * Retrieves all Books in the catalogue.
	 * 
	 * @return list of all Books in the catalogue.
	 */
	public List<Book> getAllBooks() {
		logger.info("Retrieving all books saved in the catalogue.");
		return bookRepository.findAll();
	}
	
	/**
	 * Retrieves a Book from a catalogue by its id.
	 * 
	 * @param id The identification number of a book.
	 * @return a Book with the given id, null otherwise.
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
	 * Deletes a book by its id.
	 * 
	 * @param id of the book to delete from the catalogue.
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
