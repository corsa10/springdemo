package com.example.demo.customer.dto;

import com.example.demo.customer.CustomerStatus;
import jakarta.validation.constraints.*;

public record CustomerCreateRequest(
        @NotBlank @Size(max = 100) String name,
        @NotBlank @Email @Size(max = 120) String email,
        @Size(max = 30) String phone,
        @NotNull CustomerStatus status,
        @Size(max = 1000) String remark
        ) {}
