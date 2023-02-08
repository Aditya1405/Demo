package com.example.demo.service;

import com.example.demo.dao.UserRepository;
import com.example.demo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    //get all user
    public List<User> getAllUser(){
        List<User> list=userRepository.getUser();
        return list;
    }
//    get user by id
    public User getUserById(Integer id){
        User user=null;
        user=userRepository.getUserById(id);
        return user;
    }
    public User SaveData(User user){
        return userRepository.save(user);
    }
    public void deleteUserById(Integer id){
        userRepository.deleteById(id);
    }
    public User updateUserById(Integer id,User user){
        System.out.println(user );
        return userRepository.findById(id)
                .map(employee -> {
                    employee.setName(user.getName());
                    employee.setStatus(user.getStatus());
                    employee.setCity(user.getCity());
                    return userRepository.save(employee);
                })
                .orElseGet(() -> {
                    return userRepository.save(user);
                });
    }

}
