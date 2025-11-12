package com.aeroreserve.service;

import com.aeroreserve.model.User;
import com.aeroreserve.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository repo;
    public UserService(UserRepository repo) { this.repo = repo; }

    public User save(User u) { return repo.save(u); }
    public List<User> getAll() { return repo.findAll(); }
    public User getById(Long id) { return repo.findById(id).orElse(null); }
}
