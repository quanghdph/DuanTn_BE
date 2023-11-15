package com.fpt.duantn.ui.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RegisterRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String email;
    @NotBlank
    private String rawPassword;
}
