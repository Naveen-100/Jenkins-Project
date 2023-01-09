package com.sapient.asde.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JenkinsController {
    @GetMapping("/")
    public String SayHii(){
        return "Hello-World From Naveen Kalidindi";
    }
}
