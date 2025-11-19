package com.keldorn.todoclient.service;

import com.keldorn.todoclient.api.ApiClient;
import com.keldorn.todoclient.constant.ApiEndpoints;
import com.keldorn.todoclient.dto.TodoRequest;
import com.keldorn.todoclient.dto.TodoResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoUiService {

    private final ApiClient apiClient;

    public List<TodoResponse> getTodos(HttpServletRequest request) {
        TodoResponse[] todos = apiClient.get(
                ApiEndpoints.TODOS_URL,
                TodoResponse[].class,
                request
        );
        return List.of(todos);
    }

    public TodoResponse getTodoById(Long todoId, HttpServletRequest request) {
        return apiClient.get(
            ApiEndpoints.TODOS_URL + "/" + todoId,
                TodoResponse.class,
                request
        );
    }

    public void createTodo(TodoRequest todoRequest, HttpServletRequest request) {
        apiClient.post(ApiEndpoints.TODOS_URL, todoRequest, request);
    }

    public void updateTodo(Long id, TodoRequest todoRequest, HttpServletRequest request) {
        apiClient.put(ApiEndpoints.TODOS_URL + "/" + id, todoRequest, request);
    }

    public void deleteTodo(Long id, HttpServletRequest request) {
        apiClient.delete(ApiEndpoints.TODOS_URL + "/" + id, request);
    }
}
