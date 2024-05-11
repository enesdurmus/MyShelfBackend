package com.enesd.myshelfbackend.controller;

import com.enesd.myshelfbackend.dto.UserFriendDTO;
import com.enesd.myshelfbackend.model.response.GenericResponse;
import com.enesd.myshelfbackend.service.UserFriendService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/friends")
@AllArgsConstructor
public class UserFriendController {

    private final UserFriendService userFriendService;

    @PostMapping
    public ResponseEntity<GenericResponse<UserFriendDTO>> createFriendship(@RequestParam(required = true) int friendId) {
        return null;
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteFriendship(@RequestParam(required = true) int friendId) {
        return null;
    }

    @GetMapping("")
    public ResponseEntity<GenericResponse<List<UserFriendDTO>>> getFriends() {
        return null;
    }
}
