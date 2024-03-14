package com.example.demo.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class PersonRequest {
    @NonNull
    @Schema(description = "First name", example ="John")
    @Size(min=3, max = 20)
    private String firstName;
    @Schema(description = "Last name", example ="Johnson")

    private String lastName;
    @Schema(description = "Person email", example ="john@email.com")

    private String email;
    @Schema(description = "Person phone number", example ="+37061111111")

    private String phone;
}
