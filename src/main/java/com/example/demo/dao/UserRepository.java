package com.example.demo.dao;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Integer> {

    List<User> findByCity(String city);
//    write custom querries here other wise use user service to access built in crud op querries
    @Query(value = "select * from user", nativeQuery = true)
    List<User> getUser();


    @Query(value = "select * from user where id= ?", nativeQuery = true)
    User getUserById(Integer id);


}
