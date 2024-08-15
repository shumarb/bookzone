package com.bookzone.controller;

import com.bookzone.model.Librarian;
import com.bookzone.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;

@WebMvcTest(HomeController.class)
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private MockHttpSession mockSession;

    @Mock
    Person person;

   @BeforeEach
    public void setUp() {
        person = new Librarian("John Tan", "john_tan", "john_tan@sgbookcollectors.com", "MMMnnn123");
        mockSession = new MockHttpSession();
    }

    @Test
    public void testShowHome() throws Exception {
        // Add Person entity to the session
        mockSession.setAttribute("loggedInPerson", person);

        // Perform a GET request to /home
        mockMvc.perform(MockMvcRequestBuilders.get("/home")
                .session(mockSession))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("home"))
                .andExpect(MockMvcResultMatchers.model().attribute("loggedInPerson", person)
        );
    }

    @Test
    public void testLogout() throws Exception {
        // Add Person entity to the session
        mockSession.setAttribute("loggedInPerson", person);

        // Perform a POST request to /logout
        mockMvc.perform(post("/logout")
                .session(mockSession))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(flash().attribute("successfulLogout", "You have successfully logged out.")
        );
    }

}
