package edu.drexel.TrainDemo.user.controllers;

import edu.drexel.TrainDemo.user.models.User;
import edu.drexel.TrainDemo.user.repositories.UserRepository;
import edu.drexel.TrainDemo.user.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    private UserService service;

    public UserController(UserRepository repo) {
        this.service = new UserService(repo);
    }

    @RequestMapping("/greeting")
    public String greeting(Model model) {
        model.addAttribute("name", "foo");
        return "greeting";
    }

    @GetMapping("/user")
    public User getUserInfo(@AuthenticationPrincipal OAuth2User principal) {
        Integer id = principal.getAttribute("id");
        String defaultName = principal.getAttribute("name");
        return service.getOrCreateUser(id, defaultName);
    }

    @GetMapping("/user/oauth")
    public Object getUserOathInfo(@AuthenticationPrincipal OAuth2User principal) {
        return principal.getAttributes();
    }

    @GetMapping("/user/manage")
    public String manageUser(@AuthenticationPrincipal OAuth2User principal, Model model) {
        // 1. Get the from the database
        // getUserByExternalId
        // if not exist, create
        // CreateUserWithExternalId
        // 2. Display their information on the page
        String name = getUserInfo(principal).getName();
        model.addAttribute("name", name);
        return "manage_user.html";
    }

    // DEBUG ENDPOINT
    @GetMapping("/user/test")
    public Object testUser(@AuthenticationPrincipal OAuth2User principal) {
        return null;
    }

}
