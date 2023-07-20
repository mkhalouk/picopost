package com.picopost.back.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picopost.back.model.Post;
import com.picopost.back.service.PostService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/post")
public class PostController {
  @Autowired
  private PostService postService;

  @GetMapping("/all")
  public ResponseEntity<Iterable<Post>> getPosts() {
      Iterable<Post> posts = postService.getPosts();
      return ResponseEntity.ok(posts);
  }

  // Get a post by id
  @GetMapping("/{id}")
  public ResponseEntity<Post> getPostById(@PathVariable("id") final Long id) {
      Optional<Post> post = postService.getPost(id);
      if (post.isPresent()) {
          return ResponseEntity.ok(post.get());
      } else {
          return ResponseEntity.notFound().build();
      }
  }

  // Create a new post
  @PostMapping("/new")
  public ResponseEntity<Post> createUser(@RequestBody Post post) throws URISyntaxException {
      Post newUser = postService.savePost(post);
      return ResponseEntity.created(new URI("/api/posts/" + newUser.getId())).body(newUser);
  }

  // Update a post
  @PutMapping("/{id}")
  public ResponseEntity<Post> updateUser(@PathVariable Long id, @RequestBody Post newPost) {
      Post updatedPost = postService.updatePost(id, newPost);
      return updatedPost != null ? ResponseEntity.ok(updatedPost) : ResponseEntity.notFound().build();
  }

  // Delete a post
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
      postService.deletePost(id);
      return ResponseEntity.ok().build();
  }
}
