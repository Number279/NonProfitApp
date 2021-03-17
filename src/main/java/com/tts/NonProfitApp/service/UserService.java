package com.tts.NonProfitApp.service;

import com.tts.NonProfitApp.model.User;
import com.tts.NonProfitApp.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    User findByUsername(String username);
    Optional<User> findById(Long id);
    List<User> findAll();
    void save(User user);
    User saveNewUser(User user);
    User getLoggedInUser();
    void updateCart(Map<Product, Integer> cart);
    List<User> sortByCity(String city);

}
