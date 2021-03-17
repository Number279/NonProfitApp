package com.tts.NonProfitApp.controller;

import com.tts.NonProfitApp.model.Product;
import com.tts.NonProfitApp.service.ProductService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Data
@Controller
@ControllerAdvice
public class MainController {

    @Autowired
    ProductService productService;

    @GetMapping
    public String main() {return "main";}

    @ModelAttribute("products")
    public List<Product> products() {return productService.findAll();}

    @ModelAttribute("categories")
    public List<String> categories() {return productService.findDistinctCategories();}

    @GetMapping("/about")
    public String help() {return "about";}
}
