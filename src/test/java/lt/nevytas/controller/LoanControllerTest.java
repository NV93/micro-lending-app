package lt.nevytas.controller;

import lt.nevytas.entity.Loan;
import lt.nevytas.entity.User;
import lt.nevytas.service.LoanService;
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
@WebMvcTest(LoanController.class)
public class LoanControllerTest {
    private User user = new User("1234","Test","Testovic","abc","123456","abc@ab.com");

    @Autowired
    MockMvc mvc;

    @MockBean
    LoanService loanService;

    @Test
    public void getAllLoansByUserIdShouldReturnSomeLoans() throws Exception{
        given(this.loanService.getAllLoansByUserId(1))
                .willReturn(Arrays.asList(new Loan(123.45, 30
                        ,user)));
        this.mvc.perform(get("/users/1/loans").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("[{'ammount' : 123.45, 'term' : 30}]"));
    }


}