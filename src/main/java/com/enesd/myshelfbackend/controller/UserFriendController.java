package com.enesd.myshelfbackend.controller;

import com.enesd.myshelfbackend.dto.UserFriendDTO;
import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.response.GenericResponse;
import com.enesd.myshelfbackend.service.FriendshipService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/friends")
@AllArgsConstructor
public class UserFriendController {

    private final FriendshipService friendshipService;

    @PostMapping
    public ResponseEntity<GenericResponse<UserFriendDTO>> createFriendship(@RequestParam(required = true) UUID friendId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(GenericResponse.success(friendshipService.createFriendship(user.getId(), friendId)));
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteFriendship(@RequestParam(required = true) int friendId) {
        return null;
    }

    @GetMapping("")
    public ResponseEntity<GenericResponse<List<UserFriendDTO>>> getFriends() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(GenericResponse.success(friendshipService.getUserFriends(user.getId())));
    }
}
