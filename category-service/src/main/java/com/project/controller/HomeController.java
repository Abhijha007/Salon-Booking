package com.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {           //class to test the api 

    @GetMapping
    public String HomeControllerHandler(){
        return "category microservice";
    }
}
