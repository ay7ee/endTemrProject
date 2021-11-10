package com.example.project.repositories;

import com.example.project.models.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Long> {
  Users findAllByEmailAndPassword(String email, String password);
}
