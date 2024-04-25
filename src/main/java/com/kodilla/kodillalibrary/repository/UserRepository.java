package com.kodilla.kodillalibrary.repository;

import com.kodilla.kodillalibrary.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
List<User>findAll();
Optional<User>findById(Integer userId);
User save(User user);
}
