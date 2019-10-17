package com.ecom.shopping.service;

import com.ecom.shopping.model.Users;
import com.ecom.shopping.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.security.Principal;
import java.util.List;

@Component
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Users addUsers(Users users)
    {
        return userRepository.save(users);
    }
    public String delUsers()
    {
        userRepository.deleteAll();
        return "done";
    }

    public List<Users> showUser() {
        return userRepository.findAll();
    }

    public Integer getUserId(Principal principal) {
        String email=principal.getName();
        return userRepository.findByEmail(email).getUserId();
    }

    public Users callUser(Principal principal)
    {
        return userRepository.findByEmail(principal.getName());
    }


    public Users changeUserDetails(Users users) {
        Users oldUser = userRepository.findByUserId(users.getUserId());

        oldUser.setName(users.getName());
        oldUser.setEmail(users.getEmail());
        oldUser.setPassword(users.getPassword());
        oldUser.setPhoneNumber(users.getPhoneNumber());
        userRepository.saveAndFlush(oldUser);
        return oldUser;
    }
}
