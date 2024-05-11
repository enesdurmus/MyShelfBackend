package com.enesd.myshelfbackend.service;

import com.enesd.myshelfbackend.dto.UserFriendDTO;
import com.enesd.myshelfbackend.model.entities.UserFriend;
import com.enesd.myshelfbackend.repository.jpa.UserFriendRepository;
import com.enesd.myshelfbackend.utils.CustomModelMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserFriendService {

    private final UserFriendRepository userFriendRepository;

    private final ModelMapper modelMapper;

    public UserFriendDTO createFriendship(UUID userId, UUID friendId) {
        UserFriend userFriend = new UserFriend();
        userFriend.setUserId(userId);
        userFriend.setFriendId(friendId);
        UserFriend savedUserFriend = userFriendRepository.save(userFriend);
        return modelMapper.map(savedUserFriend, UserFriendDTO.class);
    }

    public List<UserFriendDTO> getUserFriends(UUID userId) {
        List<UserFriend> userFriends = userFriendRepository.findAllByUserIdOrFriendId(userId);
        return ((CustomModelMapper) modelMapper).mapAll(userFriends, UserFriendDTO.class);
    }
}
