package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    @Qualifier(value = "userServiceImpl")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value ="/new")
    public String newUserForm(Model model){
        model.addAttribute("newuser", new User());
        return "adduser";
    }

    @PostMapping()
    public String addUser(User user/*, @RequestParam(value = "role") String... roles*/){
//        user.getRoles().add(new Role(roles));

        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping()
    public String listUsers(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @GetMapping(value = "/user/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id){
        model.addAttribute("user", userService.getUserById(id));
        return "edituser";
    }

    @PostMapping("/user/{id}/edit")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id){
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/user/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String getUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute(userService.getUserById(id));
        return "getuserbyid";
     }

     @PostMapping("/user/{id}/delete")
    public String removeUser(@PathVariable("id") Long id){
        userService.removeUserById(id);
        return "redirect:/admin";
    }
}
