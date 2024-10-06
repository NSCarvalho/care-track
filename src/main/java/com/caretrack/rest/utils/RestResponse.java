package com.caretrack.rest.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponse<T> {
    private String message;
    private boolean success;
    private T data;

    private RestResponse(T data, String message) {
        this.success = true;
        this.message = message;
        this.data = data;
    }

    private RestResponse(String message) {
        this.success = false;
        this.message = message;
    }

    public static <T> RestResponse<T> success(T data, String message) {
        return new RestResponse<>(data, message);
    }

    public static <T> RestResponse<T> error(String message) {
        return new RestResponse<>(message);
    }
}