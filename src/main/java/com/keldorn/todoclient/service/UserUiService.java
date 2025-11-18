package com.keldorn.todoclient.service;

import com.keldorn.todoclient.api.ApiClient;
import com.keldorn.todoclient.constant.ApiEndpoints;
import com.keldorn.todoclient.dto.UserRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserUiService {

    private final ApiClient apiClient;

    public void register(UserRequest userRequest, HttpServletRequest servletRequest) {
        apiClient.post(ApiEndpoints.USERS_URL, userRequest, servletRequest);
    }
}