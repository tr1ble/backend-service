package com.epam.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
  @GetMapping("/")
  public String getHome() {
    return "index";
  }

  @GetMapping("/login")
  public String getLogin() {
    return "login";
  }
}
