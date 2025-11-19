package com.keldorn.todoclient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorResponse {

    private String type;
    private String title;
    private String details;
    private int statusCode;
}
