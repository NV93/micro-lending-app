package lt.nevytas.repository;

import lt.nevytas.entity.User;
import lt.nevytas.repository.UserRepository;
import org.junit.Test;
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
public class UserRepositoryTest {

    private User testUser = new User("1234","Test","Testovic","abc","123456","abc@ab.com");

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByIdShouldReturnUser() throws Exception {
        this.testEntityManager.persist(testUser);
        User user = this.userRepository.findOne(testUser.getId());
        assertThat(user.getSurname()).isEqualTo("Testovic");
        assertThat(user.getPersonsId()).isEqualTo("1234");
    }

    @Test
    public void findByIdWhenNoUserShouldReturnNull() throws Exception {
        this.testEntityManager.persist(testUser);
        User user = this.userRepository.findByPersonsId("5432");
        assertThat(user).isNull();
    }

}