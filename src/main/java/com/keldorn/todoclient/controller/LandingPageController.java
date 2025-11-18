package com.keldorn.todoclient.controller;

import com.keldorn.todoclient.api.ApiClient;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LandingPageController {

    private final ApiClient apiClient;

    @GetMapping("/")
    public String landing(HttpServletRequest request) {
        return "index";
    }
}
