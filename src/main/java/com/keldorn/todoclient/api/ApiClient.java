package com.keldorn.todoclient.api;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ApiClient {

    private final RestTemplate restTemplate;
    @Value("${api.base.url}")
    private String apiBaseUrl;

    private HttpHeaders forwardSessionCookies(HttpServletRequest request) {

        HttpHeaders headers = new HttpHeaders();
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("JSESSIONID".equals(cookie.getName())) {
                    headers.add("Cookie", "JSESSIONID=" + cookie.getValue());
                }
            }
        }
        return headers;
    }

    private <T> T exchange(String path, HttpMethod method, Object body, Class<T> responseType, HttpServletRequest request) {

        HttpHeaders headers = forwardSessionCookies(request);
        HttpEntity<Object> entity = new HttpEntity<>(body, headers);

        return restTemplate.exchange(apiBaseUrl + path, method, entity, responseType).getBody();
    }

    public <T> T get(String path, Class<T> type, HttpServletRequest request) {
        return exchange(path, HttpMethod.GET, null, type, request);
    }

    public void post(String path, Object body, HttpServletRequest request) {
        exchange(path, HttpMethod.POST, body, Void.class, request);
    }

    public void delete(String path, HttpServletRequest request) {
        exchange(path, HttpMethod.DELETE, null, Void.class, request);
    }

    public void patch(String path, Object body, HttpServletRequest request) {
        exchange(path, HttpMethod.PATCH, body, Void.class, request);
    }

    public void put(String path, Object body, HttpServletRequest request) {
        exchange(path, HttpMethod.PUT, body, Void.class, request);
    }


}
