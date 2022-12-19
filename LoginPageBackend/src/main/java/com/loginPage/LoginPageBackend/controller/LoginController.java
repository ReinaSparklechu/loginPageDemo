package com.loginPage.LoginPageBackend.controller;


import com.loginPage.LoginPageBackend.domain.Manager;
import com.loginPage.LoginPageBackend.domain.User;
import com.loginPage.LoginPageBackend.repositories.ManagerRepository;
import com.loginPage.LoginPageBackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;
import com.thedeanda.lorem.LoremIpsum;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Random;

@RestController
public class LoginController {
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ManagerRepository managerRepository;

    @GetMapping("/userDetails")
    public User auth(Authentication auth){
        var user = userRepository.findById(auth.getPrincipal().toString()).block();
        return user;
    }

    @PostMapping("/signUp")
    public void register(@RequestBody User newUser){
        var user = new User();
        user.setUsername(newUser.getUsername());
        user.setPassword(encoder.encode(newUser.getPassword()));
        user.getAuthorityList().add("USER");
        userRepository.save(user).block();
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/userList")
    public List<User> users(Authentication authentication){
        var manager = managerRepository.findById(authentication.getPrincipal().toString()).block();
        var list = manager.getUsers();
        return userRepository.findAllById(list).collectList().block();
    }
    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping("/generateUsers")
    public void generateUser(Authentication authentication, @RequestParam int noUsers){
        Random rng = new Random();
        var generator = new LoremIpsum();
        var manager = managerRepository.findById(authentication.getPrincipal().toString()).onErrorReturn(new Manager(userRepository.findById(authentication.getPrincipal().toString()).block())).block();
        for(int i =0; i< noUsers; i++){
            var user = new User();
            user.setFullName(generator.getName());
            user.setUsername(user.getFullName().split(" ")[0] +String.valueOf(rng.nextInt(9999)));
            user.setPassword(encoder.encode(generator.getWords(1)));
            user.getAuthorityList().add("USER");
            manager.getUsers().add(user.getUsername());
            userRepository.save(user).block();
        }
        managerRepository.save(manager).block();
    }
    @RequestMapping(method = RequestMethod.OPTIONS, path = "/logout")
    public void logout(){
        System.out.println("logging out");
    }
    @GetMapping("/logoutSuccess")
    public String success(HttpServletResponse response){
        response.setHeader("set-cookie", "JSESSIONID=; Max-Age=0; Expires=Thu, 01 Jan 1970 00:00:10 GMT; Path=/; Secure; SameSite=None");
        response.setStatus(202);
        return "Logged out!";
    }
}
