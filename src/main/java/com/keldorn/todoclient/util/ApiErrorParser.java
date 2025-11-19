package com.keldorn.todoclient.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keldorn.todoclient.dto.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ApiErrorParser {

    private static ObjectMapper mapper = new ObjectMapper();
    private static String INTERNAL_ERROR = "Internal Server Error";
    private static String UNKNOWN_ERROR = "Unknown error";

    public static String getErrorMessage(String errorMsg) {
        log.warn("ERROR MESSAGE RECEIVED: {}", errorMsg);
        if (errorMsg == null) {
            return UNKNOWN_ERROR;
        }
        try {
            int start = errorMsg.indexOf("{");
            int end = errorMsg.indexOf("}");

            if (start == -1 || end == -1 || start >= end) {
                return INTERNAL_ERROR;
            }

            String json = errorMsg.substring(start, end + 1);
            ApiErrorResponse errorResponse = mapper.readValue(json, ApiErrorResponse.class);

            return errorResponse.getDetails() != null ? errorResponse.getDetails() : UNKNOWN_ERROR;
        } catch (Exception e) {
            return INTERNAL_ERROR;
        }
    }
}
