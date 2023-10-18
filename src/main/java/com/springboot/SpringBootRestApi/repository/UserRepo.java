package com.springboot.SpringBootRestApi.repository;

import com.springboot.SpringBootRestApi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    public User findByUserName(String userName);
}
