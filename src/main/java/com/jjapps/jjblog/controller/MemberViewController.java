package com.jjapps.jjblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberViewController {
    @GetMapping("/login")
    public String login(){
        return "oauthLogin.html";
    }

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }
}
