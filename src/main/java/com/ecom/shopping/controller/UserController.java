package com.ecom.shopping.controller;

import com.ecom.shopping.model.Users;
import com.ecom.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
public class UserController
{
    @Autowired
    UserService userService;

    @GetMapping("/show")
    public List<Users> show()
    {
        return userService.showUser();
    }

    @PostMapping(path = "/addUsers",consumes = "application/json",produces = "application/json")
    public Users addUser(@RequestBody Users users)
    {
        return userService.addUsers(users);
    }
   /* @GetMapping(path = "/showUser",consumes = "application/json",produces = "applicaion/json")
    public  Users getUser(@PathVariable(name= "userid")int userid)
    {

        return userService.getUserId();
    }*/
    @GetMapping(path = "/checkUser", produces = "application/json" )
    public String checkUser()
    {
        return "\"valid\"";
    }

    @GetMapping(value = "/getuser",produces = "application/json")
    public Users callUsers(Principal principal)
    {
        return userService.callUser(principal);
    }


    @PostMapping("/editUser")
    public Users editUsers(@RequestBody Users user)
    {
        return userService.changeUserDetails(user);
    }
}
