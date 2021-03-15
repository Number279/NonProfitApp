package com.tts.NonProfitApp.service;

import com.tts.NonProfitApp.model.NonProf;
import com.tts.NonProfitApp.model.Role;
import com.tts.NonProfitApp.repository.NonProfRepository;
import com.tts.NonProfitApp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class NonProfServiceImpl implements NonProfService{
    private NonProfRepository nonProfRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public NonProfServiceImpl(NonProfRepository nonProfRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.nonProfRepository = nonProfRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public NonProf findByUsername(String nonProfitName) {
        return nonProfRepository.findByNonProfitName();
    }

    @Override
    public List<NonProf> findAll() {
        return (List<NonProf>) nonProfRepository.findAll();
    }

    @Override
    public void save(NonProf nonProf) {
        nonProfRepository.save(nonProf);
    }

    @Override
    public NonProf saveNewUser(NonProf nonProf) {
        nonProf.setPassword(bCryptPasswordEncoder.encode(nonProf.getPassword()));
        nonProf.setActive(1);
        Role nonProfRole = roleRepository.findByRole("USER");
        nonProf.setRoles(new HashSet<Role>(Arrays.asList(nonProfRole)));
        return nonProfRepository.save(nonProf);
    }

    @Override
    public NonProf getLoggedInUser() {
        String loggedInUsername = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return findByUsername(loggedInUsername);
    }

    public void updateHaves(Map<Product> haves){
        NonProf nonProf = getLoggedInUser();
        nonProf.setHaves(haves);
        save(nonProf);
    }
}
