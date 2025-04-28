package com.management.gatepass.Services;

import com.management.gatepass.HttpBody.AuthLoginBody;
import com.management.gatepass.Entity.Role;
import com.management.gatepass.Entity.User;
import com.management.gatepass.Util.JwtTokenProvider;
import com.management.gatepass.Repository.mongo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LoginActivityService implements UserDetailsService{
    private static final Logger LOG = LoggerFactory.getLogger(LoginActivityService.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public Map<Object, Object> getAuthDetails(AuthLoginBody data) throws BadCredentialsException {
        String username = data.getEmail();
        // TODO securing sensitive data.
        String password = data.getPassword();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        String token = jwtTokenProvider.createToken(username, userRepository.findByEmail(username).getRoles());
        Role roles = userRepository.findByEmail(username).getRoles();
        Map<Object, Object> model = new HashMap<>();
        model.put("username", username);
        model.put("token", token);
        model.put("roles", roles);
        return model;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);
        if(user != null) {
            List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
            return buildUserForAuthentication(user, authorities);
        } else {
            throw new UsernameNotFoundException("Username not found. Try with different username");
        }
    }

    private List<GrantedAuthority> getUserAuthority(Role userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(userRoles.getRole()));
        /*userRoles.forEach((role) -> {
        }); When role was set/list*/
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    public String addUser(User user) {
        String userId = user.getUserId();

        LOG.info("Checking is user is already present or not....");
        String status = userRepository.findById(userId).isPresent() ? "User already exists" : "New user is being registered";
        LOG.info("User Registration status: {}", status);

        userRepository.save(user);

        return status;
    }
}
