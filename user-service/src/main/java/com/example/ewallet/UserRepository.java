package com.example.ewallet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "Select * from user where username=?1", nativeQuery = true)
    User findByUsername(String username);
}
