package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

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
                    "email": "sreeja@gmail.com",
                    "course": "CSE"
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
                "email": "ram@gmail.com",
                "course": "ECE"
            }
            """;

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJson))
                .andExpect(status().isOk());

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Ram"))
                .andExpect(jsonPath("$[0].email").value("ram@gmail.com"))
                .andExpect(jsonPath("$[0].course").value("ECE"));
    }
    @Test
    void testDeleteStudent() throws Exception {

        String studentJson = """
            {
                "name": "Anu",
                "email": "anu@gmail.com",
                "course": "IT"
            }
            """;

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJson))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/students/1"))
                .andExpect(status().isOk());
    }
}
