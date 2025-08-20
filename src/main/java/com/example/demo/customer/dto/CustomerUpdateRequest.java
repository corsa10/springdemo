package com.example.demo.customer.dto;

import com.example.demo.customer.CustomerStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CustomerUpdateRequest(
        @NotBlank @Size(max = 100) String name,
        @NotBlank @Email @Size String email,
        @Size(max = 30) String phone,
        @NotNull CustomerStatus status,
        @Size(max = 1000) String remark
        ) {
}
