package com.example.backendintegration.repository;

import com.example.backendintegration.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CustomizedRepository<User, Integer>{
    @Query("SELECT u FROM User u WHERE u.username=:username")
    User findUser(@Param("username") String username);
}
