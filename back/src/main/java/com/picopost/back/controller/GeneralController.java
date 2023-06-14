package com.picopost.back.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000") // TODO: make the link dynamic
@RestController
public class GeneralController {
  @GetMapping("/test")
  public String test() {
    return "Hello World from the Back!";
  }
}
