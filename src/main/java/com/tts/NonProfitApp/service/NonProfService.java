package com.tts.NonProfitApp.service;

import com.tts.NonProfitApp.model.NonProf;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface NonProfService {

    NonProf findByUsername(String nonProfitName);
    List<NonProf> findAll();
    void save(NonProf nonProf);
    NonProf saveNewUser(NonProf nonProf);
    NonProf getLoggedInUser();
    void updateHaves(Map<Product> haves);

}
