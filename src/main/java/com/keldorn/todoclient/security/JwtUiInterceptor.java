package com.keldorn.todoclient.security;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class JwtUiInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String token = (String) request.getSession().getAttribute("JWT");

        if (token == null
            && !request.getRequestURI().startsWith("/login")
            && !request.getRequestURI().startsWith("/register")
            && !request.getRequestURI().startsWith("/")
        ) {
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }
}
