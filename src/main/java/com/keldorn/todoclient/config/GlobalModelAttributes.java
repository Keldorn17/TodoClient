package com.keldorn.todoclient.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalModelAttributes {

    @ModelAttribute("loggedIn")
    public boolean addLoginStatus(HttpServletRequest request) {
        String token = (String) request.getSession().getAttribute("JWT");
        return token != null;
    }
}