package com.epam.learn.controller;

import com.epam.learn.api.InfoApi;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController implements InfoApi {

  @Override
  public String about() {
    return "Information about application";
  }
}
