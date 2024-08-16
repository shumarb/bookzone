/***
 * This class illustrates the attributes and behaviours of a Book object,
 * as well as the annotations of Spring used to map entities in the  database.
 * Every book object is stored in a table called catalogue.
 * 
 * @author Sheikh Umar
 */
package com.bookzone.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

	/***
	 * A book's identification number.
	 * Every book will have a unique identification number
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/**
	 * A book's title.
	 */
	private String title;
	
	/**
	 * A book's author.
	 */
	private String author;
	
	/**
	 * A book's category
	 * 
	 * Example: Fantasy.
	 */
	private String category;
	
	/**
	 * A book's year of publication.
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
