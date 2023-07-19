package com.picopost.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picopost.back.model.Post;
import com.picopost.back.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
  
}
