package lt.nevytas.controller;

import lt.nevytas.entity.User;
import lt.nevytas.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by nevyt
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void findUserByIdShouldReturnUserDetails() throws Exception {
        given(this.userService.getUserById(1))
                .willReturn(new User("1234","Test","Testovic","abc","123456","abc@ab.com"));
        this.mvc.perform(get("/users/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content()
                .json("{'personsId' : '1234', 'name' : 'Test', 'surname' : 'Testovic', 'address' : 'abc', 'phoneNumber' : '123456', 'email' : 'abc@ab.com'}"));
    }

    @Test
    public void findAllUsersShouldReturnUserJsonList() throws Exception {
        given(this.userService.getAllUsers())
                .willReturn(Arrays
                        .asList(new User("1234","Test","Testovic","abc","123456","abc@ab.com"),
                                new User("1235","Test1","Testovic1","abc1","123457","abc1@ab.com")));
        this.mvc.perform(get("/users").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("[{'personsId' : '1234', 'name' : 'Test', 'surname' : 'Testovic', 'address' : 'abc', 'phoneNumber' : '123456', 'email' : 'abc@ab.com'}," +
                                "{'personsId' : '1235', 'name' : 'Test1', 'surname' : 'Testovic1', 'address' : 'abc1', 'phoneNumber' : '123457', 'email' : 'abc1@ab.com'}]"));
    }

}