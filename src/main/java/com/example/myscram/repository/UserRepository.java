package com.example.myscram.repository;

import com.example.myscram.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User getUserByUsername(String name);
    public User getUserByEmail(String email);
}
