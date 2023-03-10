package com.douglasventura.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglasventura.workshopmongo.domain.User;
import com.douglasventura.workshopmongo.dto.UserDTO;
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

  public User insert(User obj) {
    User user = userRepository.insert(obj);
    return user;
  }

  public User fromDTO(UserDTO userDTO) {
    User user = new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    return user;
  }

  public void delete(String id) {
    findById(id); // checks if user exists
    userRepository.deleteById(id);
  }

  // "obj" from request 
  public User update(User obj) {
    User newUser = findById(obj.getId());
    updateData(newUser, obj);
    return userRepository.save(newUser);
  }

  private void updateData(User newUser, User obj) {
    newUser.setName(obj.getName());
    newUser.setEmail(obj.getEmail());
  }
}
