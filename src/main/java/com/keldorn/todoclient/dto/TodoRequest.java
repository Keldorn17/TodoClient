package com.keldorn.todoclient.dto;

import com.keldorn.todoclient.enums.Priority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoRequest {

    private String title;
    private String description;
    private LocalDateTime dueDate;
    private Boolean completed;
    private Priority priority;
}
