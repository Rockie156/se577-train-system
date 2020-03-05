package edu.drexel.TrainDemo.user.controllers;

import edu.drexel.TrainDemo.user.models.User;
import edu.drexel.TrainDemo.user.repositories.UserRepository;
import edu.drexel.TrainDemo.user.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class UserController {

    private UserRepository repo;
    private UserService service;

    public UserController(UserRepository repo) {
        this.repo = repo;
        this.service = new UserService(repo);
    }

    @GetMapping("/user")
    public Object user(@AuthenticationPrincipal OAuth2User principal) {
        return principal.getAttributes();
    }

    @GetMapping("/user/manage")
    public String user() {
        // 1. Get the from the database
            // getUserByExternalId
            // if not exist, create
            // CreateUserWithExternalId
        // 2. Display their information on the page
        return "Hello World";
    }

    // DEBUG ENDPOINT
    @GetMapping("/user/test")
    public String testUser(@AuthenticationPrincipal OAuth2User principal) {
        Integer id = principal.getAttribute("id");
        String defaultName = principal.getAttribute("name");
        return service.getOrCreateUser(id, defaultName).toString();
    }

}
