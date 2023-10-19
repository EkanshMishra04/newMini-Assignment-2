package com.nagarro.javaMiniAssignment2.repository;

import com.nagarro.javaMiniAssignment2.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryInterface extends JpaRepository<User, Long> {}
