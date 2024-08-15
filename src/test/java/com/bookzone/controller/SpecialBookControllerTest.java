package com.bookzone.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import com.bookzone.model.SpecialBook;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import com.bookzone.service.SpecialBookService;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class SpecialBookControllerTest {

    @Mock
    private SpecialBookService specialBookService;

    @Mock
    private Model model;

    @InjectMocks
    private SpecialBookController specialBookController;

    @Test
    void testDeleteSpecialBook() {
        long id = 4;
        String expectedRedirect = "redirect:/specials";
        String actualRedirect = specialBookController.deleteSpecialBook(id);
        verify(specialBookService, times(1)).deleteSpecialBookById(id);
        assertEquals(expectedRedirect, actualRedirect);
    }

    @Test
    void testGetSpecialBooks() {
        List<SpecialBook> specialBookList = new ArrayList<>();
        specialBookList.add(new SpecialBook());
        specialBookList.add(new SpecialBook());
        Model model = mock(Model.class);

        when(specialBookService.getAllSpecialBooks()).thenReturn(specialBookList);
        String viewName = specialBookController.getSpecialBooks(model);

        assertEquals("specials", viewName);
        verify(model, times(1)).addAttribute("book", specialBookList);
    }

}
