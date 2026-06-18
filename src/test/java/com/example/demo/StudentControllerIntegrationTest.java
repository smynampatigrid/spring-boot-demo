package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateStudent() throws Exception {

        String studentJson = """
            {
                "name": "Sreeja",
                "email": "sreeja@gmail.com"
            }
            """;

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJson))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllStudents() throws Exception {

        String studentJson = """
            {
                "name": "Ram",
                "email": "ram@gmail.com"
            }
            """;

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJson))
                .andExpect(status().isOk());

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].name", hasItem("Ram")))
                .andExpect(jsonPath("$[*].email", hasItem("ram@gmail.com")));
    }


}

