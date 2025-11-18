package com.keldorn.todoclient.service;

import com.keldorn.todoclient.api.ApiClient;
import com.keldorn.todoclient.constant.ApiEndpoints;
import com.keldorn.todoclient.dto.JwtResponse;
import com.keldorn.todoclient.dto.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUiService {

    private final ApiClient apiClient;

    public boolean login(LoginRequest loginRequest, HttpServletRequest request) {
        JwtResponse response = apiClient.postAndReturn(
                ApiEndpoints.AUTH_LOGIN_URL,
                loginRequest,
                JwtResponse.class,
                request
        );

        if (response == null || response.token() == null) {
            return false;
        }

        request.getSession().setAttribute("JWT", response.token());
        return true;
    }

    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }
}
