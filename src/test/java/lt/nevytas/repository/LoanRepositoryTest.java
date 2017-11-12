package lt.nevytas.repository;

import lt.nevytas.entity.Loan;
import lt.nevytas.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

/**
 * Created by nevyt
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class LoanRepositoryTest {
    private User testUser = new User("1234","Test","Testovic","abc","123456","abc@ab.com");

    private Loan testLoan1 = new Loan(123.45,60,testUser);
    private Loan testLoan2 = new Loan(556.45,90,testUser);

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired LoanRepository loanRepository;

    @Test
    public void findByUserIdShouldReturnLoans() throws Exception {
        this.testEntityManager.persist(testLoan1);
        this.testEntityManager.persist(testLoan2);

        assertThat(testLoan1.getUser().getPersonsId()).isEqualTo("1234");
        assertThat(testLoan2.getUser().getPersonsId()).isEqualTo("1234");

        assertThat(testLoan1.getTerm()).isEqualTo(60);
        assertThat(testLoan2.getTerm()).isEqualTo(90);

    }




}