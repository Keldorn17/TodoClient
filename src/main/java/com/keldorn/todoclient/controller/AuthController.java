package com.keldorn.todoclient.controller;

import com.keldorn.todoclient.dto.LoginRequest;
import com.keldorn.todoclient.dto.UserRequest;
import com.keldorn.todoclient.service.AuthUiService;
import com.keldorn.todoclient.util.ApiErrorParser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthUiService authUiService;

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequest("", ""));
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest loginRequest, HttpServletRequest request, Model model) {

        try {
            boolean success = authUiService.login(loginRequest, request);

            if (!success) {
                model.addAttribute("error", "Authentication failed, please try again.");
                return "auth/login";
            }

            return "redirect:/todos";

        } catch (Exception ex) {
            log.error("Login failed due to API error: {}", ex.getMessage());

            String errorMessage = ApiErrorParser.getErrorMessage(ex.getMessage());
            model.addAttribute("error", errorMessage);
            model.addAttribute("loginRequest", loginRequest);

            return "auth/login";
        }
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("userRequest", new UserRequest());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(
            @ModelAttribute("userRequest") @Valid UserRequest userRequest,
            BindingResult bindingResult,
            Model model,
            HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "auth/register";
        }

        try {
            authUiService.register(userRequest, request);
            return "redirect:/login?registered";

        } catch (Exception ex) {
            model.addAttribute("global", ApiErrorParser.getErrorMessage(ex.getMessage()));
            return "auth/register";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        authUiService.logout(request);
        return "redirect:/login?logout";
    }
}
