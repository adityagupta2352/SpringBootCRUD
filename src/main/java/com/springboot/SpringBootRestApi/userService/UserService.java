package com.springboot.SpringBootRestApi.userService;


import com.springboot.SpringBootRestApi.config.CustomUserDetails;
import com.springboot.SpringBootRestApi.entity.User;
import com.springboot.SpringBootRestApi.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByUserName(username);

        if (user == null){
            throw new UsernameNotFoundException("Could not found user!!");
        }
        CustomUserDetails customUserDetails= new CustomUserDetails(user);
        return customUserDetails;
    }
}
