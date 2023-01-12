package com.douglasventura.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglasventura.workshopmongo.domain.User;
import com.douglasventura.workshopmongo.repository.UserRepository;
import com.douglasventura.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<User> findAll() {
    List<User> users = userRepository.findAll();
    return users;
  }

  public User findById(String id) {
    Optional<User> user = userRepository.findById(id);
    return user.orElseThrow(() -> new ObjectNotFoundException("Object not found!"));
  }
}
