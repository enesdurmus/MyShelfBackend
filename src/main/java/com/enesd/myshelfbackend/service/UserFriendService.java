package com.enesd.myshelfbackend.service;

import com.enesd.myshelfbackend.dto.UserFriendDTO;
import com.enesd.myshelfbackend.repository.jpa.UserFriendRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserFriendService {

    private final UserFriendRepository userFriendRepository;

    public UserFriendDTO createFriendship(UUID userId, UUID friendId) {
        return null;
    }
}
