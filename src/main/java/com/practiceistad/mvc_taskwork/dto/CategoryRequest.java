package com.practiceistad.mvc_taskwork.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequest(
        @NotBlank
        @Size(max = 20)
        String name,
        String description
) {
}
