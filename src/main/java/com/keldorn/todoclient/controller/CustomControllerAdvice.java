package com.keldorn.todoclient.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;

@Slf4j
@ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(HttpClientErrorException.class)
    public String errorMapping(HttpClientErrorException e) {
        log.warn("User error: {}", e.getMessage());
        log.info("Redirecting to login");
        log.debug("Stack trace: {}", Arrays.toString(e.getStackTrace()));
        return "redirect:/login";
    }
}
