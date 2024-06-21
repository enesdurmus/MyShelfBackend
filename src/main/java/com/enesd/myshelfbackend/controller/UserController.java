package com.enesd.myshelfbackend.controller;

import com.enesd.myshelfbackend.dto.UserDTO;
import com.enesd.myshelfbackend.dto.UserPageDTO;
import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.request.UpdateDisplayNameRequest;
import com.enesd.myshelfbackend.model.response.GenericResponse;
import com.enesd.myshelfbackend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @PutMapping("/display_name")
    public ResponseEntity<Void> updateDisplayName(@Validated @RequestBody UpdateDisplayNameRequest updateDisplayNameRequest) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.updateDisplayName(user, updateDisplayNameRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping()
    public ResponseEntity<GenericResponse<UserPageDTO>> getUsersByPagination(@RequestParam(defaultValue = "0") Integer pageNo,
                                                                             @RequestParam(defaultValue = "10") Integer pageSize) {

        return ResponseEntity.ok(GenericResponse.success(userService.getUsersByPagination(pageNo, pageSize)));
    }
}
