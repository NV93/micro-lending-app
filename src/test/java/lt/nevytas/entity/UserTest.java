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
public class UserTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    public void saveShouldPersistData() throws Exception {
        User user = this.testEntityManager.persistFlushFind(new User("1234","Test","Testovic","abc","123456","abc@ab.com"));
        assertThat(user.getName()).isEqualTo("Test");
        assertThat(user.getSurname()).isEqualTo("Testovic");
        assertThat(user.getPersonsId()).isEqualTo("1234");
        assertThat(user.getAddress()).isEqualTo("abc");
        assertThat(user.getPhoneNumber()).isEqualTo("123456");
        assertThat(user.getEmail()).isEqualTo("abc@ab.com");
    }

}