package edu.drexel.TrainDemo.user.controllers;

import edu.drexel.TrainDemo.user.repositories.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class UserController {

    private UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return Collections.singletonMap("name", principal.getAttribute("name"));
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
    public String testUser() {
        return repo.findByExternalId(1).toString();
    }

}
