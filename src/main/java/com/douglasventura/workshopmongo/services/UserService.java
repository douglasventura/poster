package com.douglasventura.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglasventura.workshopmongo.domain.User;
import com.douglasventura.workshopmongo.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<User> findAll() {
    List<User> users = userRepository.findAll();
    return users;
  }
}
