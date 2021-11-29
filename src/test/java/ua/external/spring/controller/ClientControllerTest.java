package ua.external.spring.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("test.user@gmail.com")
@TestPropertySource("/application-test.properties")
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void validClientTest() throws Exception {
        this.mockMvc.perform(post("/client/info")
                .param("name", "Yulia")
                .param("age", "5")
                .param("height", "50")
                .param("weight", "10"))
                .andDo(print()).andExpect(status().is4xxClientError());
    }

    @Test
    public void createClientTest() throws Exception {
        this.mockMvc.perform(post("/client/info")
                .param("name", "Yulia")
                .param("age", "20")
                .param("height", "165")
                .param("weight", "60")
                .param("gender", "1")
                .param("activity", "6")
                .param("nutr_goal", "9"))
                .andDo(print()).andExpect(status().is3xxRedirection());
    }

    @Test
    public void editClientTest() throws Exception {
        this.mockMvc.perform(post("/client/edit")
                .param("name", "Yulka")
                .param("age", "20")
                .param("height", "165")
                .param("weight", "60")
                .param("gender", "1")
                .param("activity", "6")
                .param("nutr_goal", "9"))
                .andDo(print()).andExpect(status().is3xxRedirection());
    }
}
