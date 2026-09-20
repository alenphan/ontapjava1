package com.example.flowops.controller;

import com.example.flowops.model.userModel;
import com.example.flowops.service.userServer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/api/v1/users")
public class userApiController {
    private final userServer userSv;

    public userApiController(userServer userSv) {
        this.userSv = userSv;
    }

    @GetMapping
    public List<userModel> getAll() {
        return userSv.getAllUser();
    }

    @GetMapping("/page")
    public Page<userModel> getPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String keyword) {
        return userSv.getPage(page, size, keyword);
    }

    @GetMapping("/search")
    public List<userModel> search(@RequestParam String name) {
        return userSv.searchUser(name);
    }

    @PostMapping
    public userModel add(@RequestBody userModel user) {
        return userSv.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        userSv.deleteUser(id);
    }
}
