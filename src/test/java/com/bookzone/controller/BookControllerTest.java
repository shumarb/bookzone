package com.bookzone.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bookzone.model.Book;
import com.bookzone.service.BookService;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    private MockMvc mockMvc;

    @Mock
    BookService bookService;

    @InjectMocks
    BookController bookController;

    Book book1;

    @BeforeEach
    void setUp() throws Exception {
        book1 = new Book(1, "Trust", "Herman Diaz", "Novel", 2022);
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    void testSAddBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/save")
                .param("title", book1.getTitle())
                .param("author", book1.getAuthor())
                .param("category", book1.getCategory())
                .param("year", String.valueOf(book1.getYear())))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());

        verify(bookService, times(1)).saveBook(any(Book.class));
    }
    
    @Test
    void testEditBook() throws Exception {
        when(bookService.getBookById(book1.getId())).thenReturn(book1);

        mockMvc.perform(MockMvcRequestBuilders.get("/editBook/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("edit"))
                .andExpect(MockMvcResultMatchers.model().attribute("book", book1))
                .andDo(MockMvcResultHandlers.print());

        verify(bookService, times(1)).getBookById(book1.getId());
    }
    
    @Test
    void testDeleteBook() throws Exception {
        when(bookService.getBookById(book1.getId())).thenReturn(book1);

        mockMvc.perform(MockMvcRequestBuilders.get("/deleteBook/1"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/catalogue"))
                .andDo(MockMvcResultHandlers.print());
    }

}
