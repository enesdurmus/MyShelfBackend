package com.enesd.myshelfbackend.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SignUpRequest {
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
