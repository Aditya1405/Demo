package com.example.demo.controller;

import com.example.demo.dao.UserRepository;
import com.example.demo.entities.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class TestController {
    @Autowired
    UserService userService;
    @GetMapping("/")
    public String firstHandler(){
       System.out.println("this is home page");
        return "home";
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> getData(String city){
//        return userService.getAllUser();
        //        ---- http status handling when there is no user return 404
        System.out.println("works get");
        List<User> list=userService.getAllUser();
        if(list.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id){
        User user=userService.getUserById(id);
        if(user==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(user));
        //return userService.getUserById(id);
    }
    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User u= null;
        try {
            u = userService.SaveData(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        //return userService.SaveData(user);
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id){
        try {
            userService.deleteUserById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id,@RequestBody User user){
        User u=null;
        try {
            u=userService.updateUserById(id, user);
            return ResponseEntity.ok().body(u);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
//        return userService.updateUserById(id, user);
    }

}
