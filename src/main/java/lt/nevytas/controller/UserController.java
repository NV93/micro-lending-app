package lt.nevytas.controller;

import lt.nevytas.entity.User;
import lt.nevytas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by nevyt
 */
@RestController

public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public void addUser(@RequestBody User user) {
        userService.save(user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    public User findUserById(@PathVariable long id) {

        return userService.getUserById(id);
    }


}
