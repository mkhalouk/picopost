package com.picopost.back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picopost.back.model.Post;
import com.picopost.back.repository.PostRepository;

@Service
public class PostService {
  @Autowired
  private PostRepository postRepository;

  public Iterable<Post> getPosts() {
    return postRepository.findAll();
  }

  public Post getPost(Long id) {
    return postRepository.findById(id).orElse(null);
  }

  public Post savePost(Post post) {
    return postRepository.save(post);
  }

  public void deletePost(Long id) {
    postRepository.deleteById(id);
  }

  // update post
  public Post updatePost(Long id, Post newPost) {
    return postRepository.findById(id)
    .map(post -> {
      if(newPost.getCaption() != null) {
        post.setCaption(newPost.getCaption());
      }
      if(newPost.getPhoto() != null) {
        post.setPhoto(newPost.getPhoto());
      }
      return postRepository.save(post);
    }).orElseGet(() -> {
      newPost.setId(id);
      return postRepository.save(newPost);
    });
  }
}