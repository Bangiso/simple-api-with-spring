package com.aphiwe.simpleApi;

import com.aphiwe.simpleApi.api.StudentController;
import com.aphiwe.simpleApi.dao.StudentDao;
import com.aphiwe.simpleApi.model.Student;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class SimpleApiApplicationTests {

    @Mock
    private StudentDao studentDao;

    @InjectMocks
    private StudentController controller;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
    }

    @Test
    public void contexLoads() throws Exception {
        assertThat(studentDao).isNotNull();
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

        when(studentDao.fetchStudents()).thenReturn(Arrays.asList(new Student(3, "Will", 78.5)));
        this.mockMvc.perform(get("/students/all"))
                .andDo(print())
                .andExpect(status().isOk())
               .andExpect(content().json(String.valueOf(new JSONArray("[{\"gpa\":78.5,\"name\":\"Will\",\"id\":3}]"))));;
    }

    @Test
    public void testFindById() throws Exception {
        when(studentDao.findById(3)).thenReturn(Optional.of(new Student(3, "Will", 78)));
        this.mockMvc.perform(get("/students/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(String.valueOf(new JSONObject("{\"name\":\"Will\",\"gpa\":78,\"id\":3}"))));
    }

    @Test
    public void tesAddStudent() throws Exception {

        when(studentDao.save(new Student(2, "Aphiwe", 66.0))).thenReturn(204);
        this.mockMvc.perform(MockMvcRequestBuilders.
                post("/students")
                .content("{\"name\":\"Aphiwe\",\"gpa\":66,\"id\":2}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("204")));
    }

    @Test
    public void testUpdateStudent() throws Exception {

        when(studentDao.updateStudent(new Student(1, "San", 89.5))).thenReturn(204);
        this.mockMvc.perform(MockMvcRequestBuilders.
                put("/students")
                .content("{\"name\":\"San\",\"gpa\":89.5,\"id\":1}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("204")));
    }
}
