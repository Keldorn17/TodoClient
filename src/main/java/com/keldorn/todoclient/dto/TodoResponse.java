package com.keldorn.todoclient.dto;

import java.time.LocalDateTime;

public record TodoResponse(Long todoId, String title, String description, LocalDateTime dueDate,
                           boolean completed, int priority) {
}
