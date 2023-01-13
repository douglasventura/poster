package com.douglasventura.workshopmongo;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.douglasventura.workshopmongo.domain.Post;
import com.douglasventura.workshopmongo.domain.User;
import com.douglasventura.workshopmongo.dto.AuthorDTO;
import com.douglasventura.workshopmongo.repository.PostRepository;
import com.douglasventura.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

  @Autowired
  UserRepository userRepository;

  @Autowired
  PostRepository postRepository;

  @Override
  public void run(String... args) throws Exception {

    userRepository.deleteAll();

    User maria = new User(null, "Maria Brown", "maria@gmail.com");
    User alex = new User(null, "Alex Green", "alex@gmail.com");
    User bob = new User(null, "Bob Grey", "bob@gmail.com");

    userRepository.saveAll(Arrays.asList(maria, alex, bob));

    postRepository.deleteAll();

    Post post1 = new Post(null, Instant.parse("2018-03-21T00:00:00Z"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
    Post post2 = new Post(null, Instant.parse("2018-03-23T00:00:00Z"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(alex));

    postRepository.saveAll(Arrays.asList(post1, post2));

  }

}
