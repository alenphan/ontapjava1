package com.example.flowops.service;

import com.example.flowops.model.userModel;
import com.example.flowops.repository.userRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userServer {
    private final userRepository repo;

    public userServer(userRepository repo) {
        this.repo = repo;
    }

    public List<userModel> getAllUser() {
        return repo.findAll();
    }

    public Page<userModel> getPage(int page, int size, String keyword) {
        Pageable pageable = PageRequest.of(Math.max(page, 0), size);
        if (keyword == null || keyword.isBlank()) {
            return repo.findAll(pageable);
        }
        return repo.findByNameContainingIgnoreCase(keyword, pageable);
    }

    public List<userModel> searchUser(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }

    public userModel createUser(userModel user) {
        user.setId(null);
        return repo.save(user);
    }

    public void deleteUser(int id) {
        repo.deleteById(id);
    }
}
