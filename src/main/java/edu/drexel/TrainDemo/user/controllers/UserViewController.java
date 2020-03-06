package edu.drexel.TrainDemo.user.controllers;

import edu.drexel.TrainDemo.user.models.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {

    private UserRestController user;

    public UserViewController(UserRestController userRestController) {
        this.user = userRestController;
    }

    @GetMapping("/user/manage")
    public String manageUser(@AuthenticationPrincipal OAuth2User principal, Model model) {
        User current = user.getUserInfo(principal);
        model.addAttribute("User", current);
        return "user/manage_user";
    }
}
