package com.tts.NonProfitApp.controller;

import com.tts.NonProfitApp.model.User;
import com.tts.NonProfitApp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthorizationController {
    private UserService userService;


    public AuthorizationController(UserService userService){
        this.userService = userService;}


    @GetMapping("/signup")
    public String registration(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/signup")
    public String createNewNonProf(@Valid User user,
                                   BindingResult bindingResult,
                                   Model model){
        User userExists = userService.findByUsername(user.getUsername());

        if(userExists != null){
            bindingResult.rejectValue("username",
                                        "error.nonProf",
                                        "Username is already taken.");
        }

        if(!bindingResult.hasErrors()) {
            userService.saveNewUser(user);
            model.addAttribute("success", "Sign up successful!");
            model.addAttribute("username", new User());
        }
        return "registration";
    }

    @GetMapping("/login")
    public String login(){return "login";}
}

