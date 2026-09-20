package com.example.flowops.controller;
import com.example.flowops.model.userModel;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import  java.util.List;
import  com.example.flowops.service.userServer;

@Controller
@RequestMapping("/users")
public class userController {
    private final userServer  userSv;
    public  userController (userServer userSv){
        this.userSv = userSv;
    }
    @GetMapping
    public String getUser(Model model){
        List<userModel> users = userSv.getAllUser();
        model.addAttribute("users", users);
        model.addAttribute("newUser", new userModel());
        model.addAttribute("keyword", "");
        return "users";
    };
    @GetMapping("/search")
    public String searchUser(
            @RequestParam String name,
            Model model) {

        List<userModel> users =
                userSv.searchUser(name);

        model.addAttribute("users", users);
        model.addAttribute("newUser", new userModel());
        model.addAttribute("keyword", name);

        return "users";
    }
    @PostMapping("/add")
    public String addUser(        @ModelAttribute userModel user
    ){
        userSv.createUser(user);
        return  "redirect:/users";
    };
    @GetMapping("/delete/{id}")
    public String deleteUser(
            @PathVariable int id) {

        userSv.deleteUser(id);

        return "redirect:/users";
    }


}
