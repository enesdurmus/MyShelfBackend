package com.enesd.myshelfbackend.services;

import com.enesd.myshelfbackend.dto.UserDTO;
import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.request.UpdateDisplayNameRequest;
import com.enesd.myshelfbackend.repository.jpa.UserRepository;
import com.enesd.myshelfbackend.utils.CustomModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CustomModelMapper modelMapper;

    @Transactional
    public void updateDisplayName(User user, UpdateDisplayNameRequest updateDisplayNameRequest) {
        userRepository.updateDisplayName(user.getId(), updateDisplayNameRequest.getDisplayName());
    }
}
