package com.bookzone.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bookzone.model.Book;
import com.bookzone.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Mock
    private Book book1;

    @Mock
    private Book book2;

    @BeforeEach
    void setUp() {
    	book1 = new Book(11, "Trust", "Herman Diaz", "Novel", 2022);
    	book2 = new Book(23, "The Reptile Room", "Daniel Handler", "Fiction", 1999);
    }

    @Test
    void test_saveBook() {
        bookService.saveBook(book1);
        verify(bookRepository, times(1)).save(book1);
    }

    @Test
    void test_getAllBooks() {
        // Arrange
        List<Book> books = new ArrayList<> ();
        books.add(book1);
        books.add(book2);
        when(bookRepository.findAll()).thenReturn(books);

        // Act
        List<Book> result = bookService.getAllBooks();

        // Assert
        assertEquals(books.size(), result.size());
        assertEquals(books, result);
    }

    @Test
    void test_getBookById() {
        // Arrange
        long id = book1.getId();
        when(bookRepository.findById(id)).thenReturn(Optional.of(book1));

        // Act
        Book result = bookService.getBookById(id);

        // Assert
        assertEquals(book1, result);
    }

    @Test
    void test_deleteBook() {
        // Arrange
        long id = book1.getId();
        doNothing().when(bookRepository).deleteById(id);

        // Act
        bookService.deleteBook(id);

        // Assert
        verify(bookRepository, times(1)).deleteById(id);
    }
    
}

