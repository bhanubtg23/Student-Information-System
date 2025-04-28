package com.management.gatepass.Controller;

import com.management.gatepass.Entity.User;
import com.management.gatepass.Services.GatepassService;
import com.management.gatepass.Services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController()
@RequestMapping("/users/v1/")
public class UserController {

    private final static Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    GatepassService gatepassService;

    /*ADMIN ACTIONS*/
    @GetMapping(value = "admin/allUsers")
    public ResponseEntity<Map> listAllUsers() {
        List<User> users = userService.findAllUsers();
        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Map<Object, Object> model = userService.getAllUsersDetails(users);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping(value = "admin/getUser/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
        LOG.info("Fetching User with id {}", id);
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()) {
            LOG.error("User with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @PutMapping(value = "admin/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        LOG.info("Updating User {}", id);

        Optional<User> currentUser = userService.findById(id);

        if (currentUser.isEmpty()) {
            LOG.error("User with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        userService.updateUser(currentUser.get(), user);
        return new ResponseEntity<>(currentUser.get(), HttpStatus.OK);
    }

    @DeleteMapping(value = "admin/deleteUser/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") String id) {
        LOG.info("Fetching & Deleting User with id {}", id);

        Optional<User> user = userService.findById(id);
        if (user.isEmpty()) {
            LOG.error("Unable to delete. User with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    /*USERS ACTIONS*/
    //TODO

}
