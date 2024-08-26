package com.enesd.myshelfbackend.unit.services;

import com.enesd.myshelfbackend.repository.jpa.UserRepository;
import com.enesd.myshelfbackend.services.UserService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;
}
