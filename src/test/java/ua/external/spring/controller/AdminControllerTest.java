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
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails("test.user@gmail.com")
    public void validProductTest() throws Exception {
        this.mockMvc.perform(post("/user/product")
                .param("name", "<script>alert('200')</script>")
                .param("calories", "400")
                .param("protein", "500")
                .param("fats", "43")
                .param("carbohydrates", "34"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
