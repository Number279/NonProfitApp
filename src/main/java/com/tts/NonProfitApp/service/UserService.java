package com.tts.NonProfitApp.service;

import com.tts.NonProfitApp.model.User;
import com.tts.NonProfitApp.model.Product;
import com.tts.NonProfitApp.model.Role;
import com.tts.NonProfitApp.repository.UserRepository;
import com.tts.NonProfitApp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService{
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }


    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }


    public void save(User user) {
        userRepository.save(user);
    }

    public User saveNewUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }


    public User getLoggedInUser() {
        String loggedInUsername = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return findByUsername(loggedInUsername);
    }

    public void updateCart(Map<Product, Integer> cart){
        User user = getLoggedInUser();
        user.setCart(cart);
        save(user);
    }

    public List<User> sortByCity(String city){
        return userRepository.findAllByCityOrderByNonProfitNameDesc(city);
    }
}
