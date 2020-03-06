package edu.drexel.TrainDemo.user.controllers;

import edu.drexel.TrainDemo.user.models.User;
import edu.drexel.TrainDemo.user.repositories.UserRepository;
import edu.drexel.TrainDemo.user.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    private UserService service;

    public UserRestController(UserRepository repo) {
        this.service = new UserService(repo);
    }

    @GetMapping("/user")
    public User getUserInfo(@AuthenticationPrincipal OAuth2User principal) {
        Integer id = principal.getAttribute("id");
        String defaultName = principal.getAttribute("name");
        return service.getOrCreateUser(id, defaultName);
    }

    @PostMapping("/user/manage/submit")
    public Object submitUserInfo(@AuthenticationPrincipal OAuth2User principal, @ModelAttribute User newUserData) {
        User originalUserData = getUserInfo(principal);
        service.saveUser(originalUserData, newUserData);
        return "<script>window.location.href = '/';</script>";
    }
}
