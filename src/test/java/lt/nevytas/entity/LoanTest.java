package lt.nevytas.entity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by nevyt
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class LoanTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void createWhenUserIsNullShouldThrowException() throws Exception {
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage("Loan must be assignet to User");
        new Loan(123.00,20,null);
    }

    @Test
    public void saveShouldPersistData() throws Exception {
        User user =  this.testEntityManager.persistFlushFind(new User("1234","Test","Testovic","abc","123456","abc@ab.com"));
        Loan loan = this.testEntityManager.persistFlushFind(new Loan(123.00,45,user));
        assertThat(loan.getAmmount()).isEqualTo(123.00);
        assertThat(loan.getTerm()).isEqualTo(45);
        assertThat(loan.getUser()).isNotNull();
    }


}