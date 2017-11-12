package lt.nevytas.service;

import lt.nevytas.entity.User;
import lt.nevytas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nevyt
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public User getUserById(long id) {
        Assert.notNull(userRepository.findOne(id), "User with given id was not found");
        return userRepository.findOne(id);
    }
    // Helper method if you don't know the users id
    public long getUserIdByPersonsId(String personsId){
        User foundUser = userRepository.findByPersonsId(personsId);
        long foundUserId = foundUser.getId();
        return foundUserId;
    }


}

