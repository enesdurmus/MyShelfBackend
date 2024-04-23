package com.enesd.myshelfbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInDTO {
    private UserDTO user;
    private String accessToken;
}
