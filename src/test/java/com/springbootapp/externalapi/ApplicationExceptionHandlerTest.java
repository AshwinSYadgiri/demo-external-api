package com.springbootapp.externalapi;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationExceptionHandlerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHandleException() throws Exception {
        mockMvc.perform(get("/users")).andExpect(status().isOk());
        
    }

    @Test
    public void testHandleExceptionisOK() throws Exception {
        mockMvc.perform(get("/user/1")).andExpect(status().isOk());
        
    }

    @Test
    public void testHandleExceptionNotFound() throws Exception {
        mockMvc.perform(get("/user/23")).andExpect(status().isNotFound());
        
    }

    
}
