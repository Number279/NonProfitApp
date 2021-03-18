package com.tts.NonProfitApp.controller;

import com.tts.NonProfitApp.model.User;
import com.tts.NonProfitApp.repository.UserRepository;
import com.tts.NonProfitApp.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Api(value = "nonProfits", description = "Rest Controller to handle Crud actions on nonProfit database.")
@RequestMapping("/nonprofits")
public class NonProfitController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @ApiResponses(value = {
            @ApiResponse(code=200, message="Successfully retrieved the nonProfit."),
            @ApiResponse(code = 401, message = "You are not authorized to view this menu item.")
    })
    @ApiOperation(value = "Get Non-Profit by ID", response = User.class,
            responseContainer = "Individual")
    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable Long id, Model model){
        return userService.findById(id);
    }

    @ApiResponses(value = {
            @ApiResponse(code=200, message="Successfully retrieved all Non-Profits."),
            @ApiResponse(code = 401, message = "You are not authorized to view this menu item.")
    })
    @ApiOperation(value = "Get all Non-Profits", response = User.class,
            responseContainer = "List")
    @GetMapping("/all")
    public Iterable<User> findAll() { return userService.findAll();}

//    @ApiResponses(value = {
//            @ApiResponse(code=200, message="Successfully retrieved the Non-Profits for this city."),
//            @ApiResponse(code = 401, message = "You are not authorized to view this menu item.")
//    })
//    @ApiOperation(value = "All non-profits for a given city.", response = User.class,
//            responseContainer = "List")
//    @GetMapping("/city")
//    public List<User> sortByCity(@RequestParam(value = "city", required = false)String city){
//        if(city!=null){
//            return userService.sortByCity(city);
//        }
//        return userService.findAll();
//    }

    @ApiResponses(value = {
            @ApiResponse(code=200, message="Successfully retrieved the Non-Profits for this city."),
            @ApiResponse(code = 401, message = "You are not authorized to view this menu item.")
    })
    @ApiOperation(value = "All non-profits for a given city.", response = User.class,
            responseContainer = "List")
    @GetMapping("/city")
    public ResponseEntity<List<User>> sortByCity(@RequestParam(value = "city", required = false)String city){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Sawyer's Header", "Access-Control-Allow-Origin: *");

        if(city!=null){
            return ResponseEntity.ok(userService.sortByCity(city));
//                    .headers(responseHeaders)
//                    .body("Response with header using ResponseEntity");
        }
        return ResponseEntity.ok(userService.findAll());
    }
}
