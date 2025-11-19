package com.keldorn.todoclient.dto;

import com.keldorn.todoclient.enums.Priority;

import java.time.LocalDateTime;

public record TodoResponse(Long todoId, String title, String description, LocalDateTime dueDate,
                           boolean completed, Priority priority) {
}
