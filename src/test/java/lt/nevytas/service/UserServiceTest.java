package lt.nevytas.service;

import lt.nevytas.entity.User;
import lt.nevytas.repository.UserRepository;
import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


/**
 * Created by nevyt on 17-Oct-17.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @Test
    public void saveEntityMethodSavesUserEntities() throws Exception {
        User testUser1 = Mockito.mock(User.class);
        User testUser2 = Mockito.mock(User.class);
        userRepository.save(testUser1);
        userRepository.save(testUser2);

        List<User> userList = new LinkedList<>();
        List spy = Mockito.spy(userList);
        spy.add(testUser1);
        spy.add(testUser2);

        Mockito.verify(spy).add(testUser1);
        Mockito.verify(spy).add(testUser2);
        assertEquals(2, spy.size());
        when(userRepository.save(testUser1)).thenReturn(testUser1);
        when(userRepository.save(testUser2)).thenReturn(testUser2);
        Mockito.verify(userRepository).save(testUser1);
        assertEquals(userRepository.save(testUser1), testUser1);
        Mockito.verify(userRepository).save(testUser2);
        assertEquals(userRepository.save(testUser2), testUser2);
    }

    @Test
    public void getUserWithInvalidIdReturnsNull() throws Exception {
        User testUser1 = Mockito.mock(User.class);
        testUser1.setId(1L);
        userRepository.save(testUser1);
        userService.getUserById(123L);
        when(userService.getUserById(123L)).thenReturn(null);
        Mockito.verify(userService).getUserById(123L);
    }

    @Test
    public void serviceGetAllMethodReturnsUserList() throws Exception {
        User testUser1 = Mockito.mock(User.class);
        User testUser2 = Mockito.mock(User.class);
        userRepository.save(testUser1);
        userRepository.save(testUser2);

        List<User> userList = new LinkedList<>();
        List spyUserList = Mockito.spy(userList);
        spyUserList.add(testUser1);
        spyUserList.add(testUser2);

        Mockito.verify(spyUserList).add(testUser1);
        Mockito.verify(spyUserList).add(testUser2);

        userService.getAllUsers();
        when(userService.getAllUsers()).thenReturn(spyUserList);
        Mockito.verify(userService).getAllUsers();

        assertEquals(userService.getAllUsers(),spyUserList);
    }

    @Test
    public void getUserByIdReturnMatchesUser() throws Exception {
        User testUser1 = Mockito.mock(User.class);
        when(testUser1.getId()).thenReturn(34L);
        User testUser2 = Mockito.mock(User.class);
        when(testUser2.getId()).thenReturn(23L);

        userRepository.findOne(23L);
        when(userRepository.findOne(23L)).thenReturn(testUser2);
        assertThat(userRepository.findOne(23L), CoreMatchers.is(testUser2));

        userRepository.findOne(34L);
        when(userRepository.findOne(34L)).thenReturn(testUser1);
        assertThat(userRepository.findOne(34L), CoreMatchers.is(testUser1));
    }
}