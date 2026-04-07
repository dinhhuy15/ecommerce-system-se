package com.example.fashionshop.modules.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateProfileRequest {
    @NotBlank(message = "Full name is required")
    private String fullName;

    @Email(message = "Invalid email format")
    private String email;

    @Size(max = 20, message = "Phone number must be at most 20 characters")
    private String phone;

    @Size(max = 255, message = "Address must be at most 255 characters")
    private String address;

    @Size(max = 500, message = "Avatar URL must be at most 500 characters")
    private String avatarUrl;

    @Size(max = 1000, message = "Bio must be at most 1000 characters")
    private String bio;
}
