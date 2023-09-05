package com.mobileshop.repos;

import com.mobileshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<User,Integer> {

    @Query("from User where name=:username")
    public  User getUser(@Param("username") String username);
}
