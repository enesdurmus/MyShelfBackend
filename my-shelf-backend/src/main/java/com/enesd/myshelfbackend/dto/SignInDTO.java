package com.enesd.myshelfbackend.dto;

import lombok.Data;

@Data
public class SignInDTO {
    private UserDTO user;
    private String accessToken;
}
