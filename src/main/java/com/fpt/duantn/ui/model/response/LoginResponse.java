package com.fpt.duantn.ui.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    Long userId;
    String username;
    String token;
}

