package com.aphiwe.simpleApi;

import com.aphiwe.simpleApi.api.StudentController;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class SimpleApiApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private StudentController controller;


    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void testIndexPage() throws Exception {
        this.mockMvc.perform(get("/students"))
                .andDo(print())
                .andExpect(status().isOk()).andExpect(content().string(containsString("Welcome to students list")));
    }

    @Test
    public void testFetchStudents() throws Exception {
        this.mockMvc.perform(get("/students/all"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testFindById() throws Exception {
        this.mockMvc.perform(get("/students/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void tesAddStudent() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.
                post("/students")
                .content("{\"name\":\"Aphiwe\",\"gpa\":66,\"id\":1}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("204")));
    }

    @Test
    public void testUpdateStudent() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.
                put("/students/1")
                .content("{\"name\":\"Aphiwe\",\"gpa\":66,\"id\":1}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("204")));
    }
}
