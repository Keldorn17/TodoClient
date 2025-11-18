package com.keldorn.todoclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthRedirectController {

    @Value("${api.base.url}")
    private String apiBaseUri;

    @GetMapping("/login")
    public String loginRedirect() {
        return String.format("redirect:%s/login", apiBaseUri);
    }

    @GetMapping("/register")
    public String registerRedirect() {
        return String.format("redirect:%s/register", apiBaseUri);
    }

    @GetMapping("/logout")
    public String logoutRedirect() {
        return String.format("redirect:%s/logout", apiBaseUri);
    }
}
