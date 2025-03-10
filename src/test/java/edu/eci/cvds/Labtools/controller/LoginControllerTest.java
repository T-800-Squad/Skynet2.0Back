package edu.eci.cvds.Labtools.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testEmailVerificationWithAGoodEmail() throws Exception {
        mockMvc.perform(get("/log")
                        .param("email", "test@mail.escuelaing.edu.co"))
                        .andExpect(status().isOk())
                        .andExpect(content().string("true"));
    }

    @Test
    public void testEmailVerificationWithBadEmail() throws Exception {
        mockMvc.perform(get("/log")
                        .param("email", "test@gmail.com"))
                        .andExpect(status().isBadRequest());
    }

}
