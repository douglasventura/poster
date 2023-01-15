package com.douglasventura.workshopmongo;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.douglasventura.workshopmongo.domain.Post;
import com.douglasventura.workshopmongo.domain.User;
import com.douglasventura.workshopmongo.dto.AuthorDTO;
import com.douglasventura.workshopmongo.dto.CommentDTO;
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

    CommentDTO c1 = new CommentDTO("Boa viagem mano!", Instant.parse("2018-03-21T00:00:00Z"), new AuthorDTO(alex));
    CommentDTO c2 = new CommentDTO("Aproveite!", Instant.parse("2018-03-22T00:00:00Z"), new AuthorDTO(bob));
    CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", Instant.parse("2018-03-23T00:00:00Z"), new AuthorDTO(alex));

    post1.getComments().addAll(Arrays.asList(c1, c2));
    post2.getComments().add(c3);

    postRepository.saveAll(Arrays.asList(post1, post2));

    maria.getPosts().addAll(Arrays.asList(post1, post2));
    userRepository.save(maria);

  }

}
