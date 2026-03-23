package com.example.secure_library_management_api.security;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


public record LoginRequest (
        @NotNull(message = "can not be null")
        @Size(min = 3,max = 15,message = "3 to 15")
        String username,

        @NotNull(message = "Password can not be null")
        String password


)
{ }
