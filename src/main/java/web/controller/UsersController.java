package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;


@Controller
@RequestMapping(value = "/user")
public class UsersController {

    private UserService userService;

    @Autowired
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value ="/new")
    public String newUserForm(Model model){
        model.addAttribute("newuser", new User());
        return "users/adduser";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("newuser") User user){
        userService.saveUser(user);
        return "redirect:/user";
    }

    @GetMapping()
    public String listUsers(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "users/users";
    }

    @GetMapping(value = "/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id){
        model.addAttribute("editUser", userService.getUserById(id));
        return "/users/edituser";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("updateUser") User user, @PathVariable("id") Long id){
        userService.updateUser(id, user);
        return "redirect:/user";
    }

    @GetMapping(value = "/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute(userService.getUserById(id));
        return "users/getuserbyid";
     }

    @DeleteMapping("/{id}")
    public String removeUser(@PathVariable("id") Long id){
        userService.removeUserById(id);
        return "redirect:/user";
    }
}
