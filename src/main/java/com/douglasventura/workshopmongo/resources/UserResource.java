package com.douglasventura.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.douglasventura.workshopmongo.domain.Post;
import com.douglasventura.workshopmongo.domain.User;
import com.douglasventura.workshopmongo.dto.UserDTO;
import com.douglasventura.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<List<UserDTO>> findAll() {
    List<User> users = userService.findAll();
    List<UserDTO> usersDTO = users.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
    return ResponseEntity.ok().body(usersDTO);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<UserDTO> findById(@PathVariable String id) {
    User user = userService.findById(id);
    UserDTO userTDO = new UserDTO(user);
    return ResponseEntity.ok().body(userTDO);
  }

  @PostMapping
  public ResponseEntity<Void> insert(@RequestBody UserDTO userDTO) {
    User user = userService.fromDTO(userDTO);
    user = userService.insert(user);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Void> update(@RequestBody UserDTO userDTO, @PathVariable String id) {
    User user = userService.fromDTO(userDTO);
    user.setId(id);
    userService.update(user);
    return ResponseEntity.noContent().build();
  }

  @GetMapping(value = "/{id}/posts")
  public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
    User user = userService.findById(id);
    return ResponseEntity.ok().body(user.getPosts());
  }

}
