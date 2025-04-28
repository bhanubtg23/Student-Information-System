package com.management.gatepass.Services;

import com.management.gatepass.Entity.Role;
import com.management.gatepass.Entity.User;
import com.management.gatepass.Repository.mongo.RoleRepository;
import com.management.gatepass.Repository.mongo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null)
            throw new BadCredentialsException("User with username: " + user.getEmail() + " already exists");
        else
            return user;
    }

    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        Role userRole = roleRepository.findByRole(user.getRoles().getRole());
        user.setRoles(userRole);
        userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    public void updateUser(User currentUser, User user) {
        //TODO
    }

    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }

    public Map<Object, Object> getAllUsersDetails(List<User> users) {
        Map<Object, Object> userDetails = new HashMap<>();
        userDetails.put("UserCount", users.size());
        userDetails.put("UserList", users);
        return  userDetails;
    }
}
