package com.tts.NonProfitApp.controller;

import com.tts.NonProfitApp.model.Product;
import com.tts.NonProfitApp.repository.ProductRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@Api(value = "AddNewProduct", description = "Adds new products for the Non-Profits")
@RequestMapping("/newProduct")
public class NewProductController {
    @Autowired
    ProductRepository productRepository;

    @RequestMapping(value = "/add", method = {RequestMethod.POST, RequestMethod.PUT})
    public String createOrUpdate(@Valid Product product){
        productRepository.save(product);
        return "/main";
    }
}
