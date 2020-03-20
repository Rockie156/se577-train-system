package edu.drexel.TrainDemo.user.controllers;

import edu.drexel.TrainDemo.user.models.UserEntity;
import edu.drexel.TrainDemo.user.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    @ResponseBody
    public UserEntity getUserInfo(@AuthenticationPrincipal OAuth2User principal) {
        return userService.getOrCreateUser(principal);
    }

    @GetMapping("/user/manage")
    public String manageUser(@AuthenticationPrincipal OAuth2User principal, Model model) {
        UserEntity current = userService.getOrCreateUser(principal);
        model.addAttribute("CurrentUser", current);
        return "user/manage_user";
    }

    @PostMapping("/user/manage/submit")
    @ResponseBody
    public Object submitUserInfo(@AuthenticationPrincipal OAuth2User principal, @ModelAttribute UserEntity newUserData) {
        userService.saveUser(principal, newUserData);
        return "<script>window.location.href = '/';</script>";
    }
}
