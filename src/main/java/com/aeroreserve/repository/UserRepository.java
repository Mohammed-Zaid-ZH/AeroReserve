package com.aeroreserve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aeroreserve.model.User;

public interface UserRepository extends JpaRepository<User, Long> {}
