package com.example.flowops.repository;

import com.example.flowops.model.userModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface userRepository extends JpaRepository<userModel, Integer> {
    List<userModel> findByNameContainingIgnoreCase(String name);

    Page<userModel> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
