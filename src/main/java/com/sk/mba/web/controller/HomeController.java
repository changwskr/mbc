package com.sk.mba.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/") // http://localhost:8080
    public String Home() {
        return "home";
    }

}
