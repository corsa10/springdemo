package com.example.demo.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;

    @Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
    public static <T>ApiResponse<T> ok(T data) {
        return ApiResponse.<T>builder().success(true).data(data).build();
    }

    public static <T>ApiResponse<T> ok(String message, T data) {
        return ApiResponse.<T>builder().success(true).message(message).data(data).build();
    }

    public static <T>ApiResponse<T> error(String message) {
        return ApiResponse.<T>builder().success(false).message(message).build();
    }
}
