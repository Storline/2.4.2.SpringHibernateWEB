package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.security.Principal;
import java.util.Optional;


@Controller
@RequestMapping("")
public class UserController {

    private UserService userService;

    @Autowired
    @Qualifier(value = "userServiceImpl")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String showUser(Principal principal, Model model) {
        Optional<User> username = userService.findByUsername(principal.getName());
        model.addAttribute("user", username);
        return "users";
    }

}