package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    @Qualifier(value = "userServiceImpl")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute(userService.getUserById(id));
        return "getuserbyid";
    }

}