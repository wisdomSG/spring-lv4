package com.example.springlv4.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @Pattern(regexp = "^[a-z0-9]{4,10}$")
    @NotBlank
    private String username;
    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$")
    @NotBlank
    private String password;

    private boolean admin = false;
    private String adminToken = "";
}
