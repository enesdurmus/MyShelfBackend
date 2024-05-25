package com.enesd.myshelfbackend.service;

import com.enesd.myshelfbackend.dto.UserFriendDTO;
import com.enesd.myshelfbackend.model.entities.Friendship;
import com.enesd.myshelfbackend.repository.jpa.FriendshipRepository;
import com.enesd.myshelfbackend.utils.CustomModelMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FriendshipService {

    private final FriendshipRepository friendshipRepository;

    private final ModelMapper modelMapper;

    public UserFriendDTO createFriendship(UUID userId, UUID friendId) {
        Friendship friendship = new Friendship();
        friendship.setUserId(userId);
        friendship.setFriendId(friendId);
        Friendship savedFriendship = friendshipRepository.save(friendship);
        return modelMapper.map(savedFriendship, UserFriendDTO.class);
    }

    public List<UserFriendDTO> getUserFriends(UUID userId) {
        List<Friendship> friendships = friendshipRepository.findAllByUserIdOrFriendId(userId);
        return ((CustomModelMapper) modelMapper).mapAll(friendships, UserFriendDTO.class);
    }
}
