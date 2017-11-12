package lt.nevytas;

import lt.nevytas.entity.User;
import lt.nevytas.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.*;

/**
 * Created by nevyt
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class MicroLendingAppApplicationIntegratioTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        given(userService.getUserById(123)).willReturn(new User("1234","Test","Testovic","abc","123456","abc@ab.com"));
    }

    @Test
    public void test(){
        ResponseEntity<String> response = this.restTemplate
                .getForEntity("/usres/{userId}", String.class, 123);
        assertThat(response.getBody().contains("Testovic"));
    }

}