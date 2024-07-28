package com.bookzone.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class IndexControllerTest {

    @Mock
    private IndexController indexController;

    @Test
    void test_showIndex() {
        String result = indexController.showIndex();
        assertEquals("index", result);
    }

}