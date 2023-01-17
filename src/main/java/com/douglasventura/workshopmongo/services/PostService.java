package com.douglasventura.workshopmongo.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglasventura.workshopmongo.domain.Post;
import com.douglasventura.workshopmongo.repository.PostRepository;
import com.douglasventura.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

  @Autowired
  private PostRepository postRepository;

  public Post findById(String id) {
    Optional<Post> post = postRepository.findById(id);
    return post.orElseThrow(() -> new ObjectNotFoundException("Object not found!"));
  }

  public List<Post> findByTitle(String text) {
    return postRepository.findByTitle(text);
  }

  public List<Post> fullSearch(String text, Instant minDate, Instant maxDate) {
    maxDate = maxDate.plusSeconds(60 * 60 * 24);
    return postRepository.fullSearch(text, minDate, maxDate);
  }

}
