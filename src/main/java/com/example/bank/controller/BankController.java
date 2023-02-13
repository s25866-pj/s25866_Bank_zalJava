package com.example.bank.controller;

import com.example.bank.model.Response;
import com.example.bank.model.User;
import com.example.bank.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class BankController {
private UserService userService;

    public BankController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.create(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/withdrawal")
    public ResponseEntity withdrawal(int id, long money) {
       if(userService.getWithdral(id,money) == Response.status.ACCEPTED){
           return ResponseEntity.accepted().build();
       }
        return ResponseEntity.badRequest().build();
    }
    @PostMapping("/addMoney")
    public ResponseEntity<Response> addmoney(int id, long money){
        if(userService.addMoney(id,money) == Response.status.ACCEPTED){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllUsers(){
        userService.deteleAll();
    }

    @GetMapping
    public ResponseEntity <List<User>> getAllUser(){
        List<User> userList= userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }
    @PostMapping("/saveToDB")
    public void saveToDatabase(){
        userService.saveDataToDB();
    }
    @PostMapping ("/readFromDB")
    public ResponseEntity <List<User>> readFromDataBase(){

        List<User> userList= userService.readFromDataBase();
        return ResponseEntity.ok(userList);
    }
}
