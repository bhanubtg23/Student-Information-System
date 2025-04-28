/*
package com.management.gatepass;

import com.management.gatepass.Entity.Role;
import com.management.gatepass.repository.mongo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class MongoApplication implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(MongoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        roleRepository.deleteAll();
        List<Role> roleList = Arrays.asList(new Role("1","ADMIN"),new Role("2","USER"));
        for(Role role: roleList)
            roleRepository.save(role);
    }

}
*/
