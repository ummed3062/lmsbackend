package com.bytexl.lmsbackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bytexl.lmsbackend.dto.RegisterRequest;
import com.bytexl.lmsbackend.entity.User;
import com.bytexl.lmsbackend.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String register(RegisterRequest request) {

        Optional<User> optionalUser = repository.findByUsername(
                request.getUsername());

        if (optionalUser.isPresent()) {
            return "Username Already Exists";
        }

        User user = new User();

        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        repository.save(user);
        return "Registration Successful";
    }

    public List<User> registeredUsers() {
        return repository.findAll();
    }
}
