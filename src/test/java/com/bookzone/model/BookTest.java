package com.bookzone.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class BookTest {

	@Mock
	Book book1;
	
	@Mock
	Book book2;
	
	@BeforeEach
	void setUp() {
		book1 = new Book(1, "King: A Life", "R.F. Kuang", "Fiction", 2023);
		book2 = new Book(2, "The Bad Beginning", "Daniel Handler", "Novel", 1999);
	}

	@Test
	void returnsCorrectTitle() {
		assertEquals("King: A Life", book1.getTitle());
	}
	
	@Test
	void doesNotReturnIncorrectTitle() {
		assertNotEquals(book2.getTitle(), book1.getTitle());
	}
	
	@Test
	void doesNotReturnNullForCorrectTitle() {
		assertNotEquals(null, book1.getAuthor());
	}
	
	@Test
	void returnsCorrectAuthor() {
		assertEquals("Daniel Handler", book2.getAuthor());
	}
	
	@Test
	void doesNotReturnIncorrectAuthor() {
		assertNotEquals(book1.getAuthor(), book2.getAuthor());
	}
	
	@Test
	void doesNotReturnNullForCorrectAuthor() {
		assertNotEquals(null, book1.getAuthor());
	}
	
	@Test
	void returnsCorrectYear() {
		assertEquals(2023, book1.getYear());
	}
	
	@Test
	void doesNotReturnIncorrectPrice() {
		assertNotEquals(book2.getYear(), book1.getYear());
	}
	
	@Test
	void returnsCorrectCategory() {
		assertEquals("Novel", book2.getCategory());
	}
	
	@Test
	void doesNotReturnIncorrectCategory() {
		assertNotEquals(book1.getCategory(), book2.getCategory());
	}
	
	@Test
	void doesNotReturnNullForCorrectUser() {
		assertNotEquals(null, book2.getCategory());
	}
	
	@Test
	void returnsCorrectId() {
		assertEquals(1, book1.getId());
	}
	
	@Test
	void doesNotReturnIncorrectId() {
		assertNotEquals(book2.getId(), book1.getId());
	}

}
