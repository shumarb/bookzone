/**
 * Represents a book entity with attributes such as title, author, category, and year of publication.
 * Maps to the "catalogue" table in the database.
 */

package com.bookzone.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "catalogue")
public class Book {

	/***
	 * Unique identification number of a book.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/**
	 * Title of the book.
	 */
	private String title;
	
	/**
	 * Author of the book.
	 */
	private String author;
	
	/**
	 * Category of the book.
	 */
	private String category;
	
	/**
	 * Year of the book.
	 */
	private int year;

	/**
	 * Default constructor required by Hibernate.
	 */
	public Book() {

	}

	/**
	 * Constructs a new {@link Book} entity with the specified details.
	 *
	 * @param title		The title of the Book.
	 * @param author	The author of the Book.
	 * @param category	The category of the Book.
	 * @param year		The year of the Book.
	 */
	public Book(String title, String author, String category, int year) {
		super();
		this.title = title;
		this.author = author;
		this.category = category;
		this.year = year;
	}

	/**
	 * Constructs a new {@link Book} entity with the specified details.
	 *
	 * @param id		The identification number of the Book.
	 * @param title		The title of the Book.
	 * @param author	The author of the Book.
	 * @param category	The category of the Book.
	 * @param year		The year of the Book.
	 */
	public Book(long id, String title, String author, String category, int year) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.category = category;
		this.year = year;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Book{" +
				"id=" + id +
				", title='" + title + '\'' +
				", author='" + author + '\'' +
				", category='" + category + '\'' +
				", year=" + year +
				'}';
	}

}
