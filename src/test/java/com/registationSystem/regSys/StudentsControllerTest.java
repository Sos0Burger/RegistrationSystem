package com.registationSystem.regSys;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.registationSystem.regSys.Controllers.StudentController;
import com.registationSystem.regSys.Models.Student;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(StudentController.class)
@AutoConfigureMockMvc
@ComponentScan({"com.registrationSystem.regSys"})
public class StudentsControllerTest {

    @Autowired
    private WebApplicationContext applicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Before("")
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }
    @Test
    public void getEmployeeTest() throws Exception {
        mockMvc.perform(get("/students")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(""))
                .andExpect(jsonPath("$.firstname").value(""));
    }
    @Test
    public void postStudentTest() throws Exception {
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();

        Student student = new Student(0,"Sex","Sex",18,0,false,-1);


        mockMvc.perform(MockMvcRequestBuilders.post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("200"));
    }

}
