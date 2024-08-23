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
	void setUp() throws Exception {
		book1 = new Book(1, "King: A Life", "R.F. Kuang", "Fiction", 2023);
		book2 = new Book(2, "The Bad Beginning", "Daniel Handler", "Novel", 1999);
	}

	@Test
	void test_returnsCorrectTitle() {
		assertEquals("King: A Life", book1.getTitle());
	}
	
	@Test
	void test_doesNotReturnIncorrectTitle() {
		assertNotEquals(book2.getTitle(), book1.getTitle());
	}
	
	@Test
	void test_doesNotReturnNullForCorrectTitle() {
		assertNotEquals(null, book1.getAuthor());
	}
	
	@Test
	void test_returnsCorrectAuthor() {
		assertEquals("Daniel Handler", book2.getAuthor());
	}
	
	@Test
	void test_doesNotReturnIncorrectAuthor() {
		assertNotEquals(book1.getAuthor(), book2.getAuthor());
	}
	
	@Test
	void test_doesNotReturnNullForCorrectAuthor() {
		assertNotEquals(null, book1.getAuthor());
	}
	
	@Test
	void test_returnsCorrectYear() {
		assertEquals(2023, book1.getYear());
	}
	
	@Test
	void test_doesNotReturnIncorrectPrice() {
		assertNotEquals(book2.getYear(), book1.getYear());
	}
	
	@Test
	void test_returnsCorrectCategory() {
		assertEquals("Novel", book2.getCategory());
	}
	
	@Test
	void test_doesNotReturnIncorrectCategory() {
		assertNotEquals(book1.getCategory(), book2.getCategory());
	}
	
	@Test
	void test_doesNotReturnNullForCorrectUser() {
		assertNotEquals(null, book2.getCategory());
	}
	
	@Test
	void test_returnsCorrectId() {
		assertEquals(1, book1.getId());
	}
	
	@Test
	void test_doesNotReturnIncorrectId() {
		assertNotEquals(book2.getId(), book1.getId());
	}

}
