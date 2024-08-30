package com.mockproject.group3.repository;

import com.mockproject.group3.model.Users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findById(int userId);
}