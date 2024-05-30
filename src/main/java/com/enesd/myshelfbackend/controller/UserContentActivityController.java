package com.enesd.myshelfbackend.controller;

import com.enesd.myshelfbackend.dto.ContentActivityDTO;
import com.enesd.myshelfbackend.enums.ContentType;
import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.request.CreateContentActivityRequest;
import com.enesd.myshelfbackend.model.response.GenericResponse;
import com.enesd.myshelfbackend.services.UserContentActivityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user_content_activities")
@AllArgsConstructor
public class UserContentActivityController {

    private final UserContentActivityService userContentActivityService;

    @PreAuthorize("hasAuthority('APP_USER') or hasAuthority('ADMIN')")
    @PostMapping("")
    public ResponseEntity<GenericResponse<ContentActivityDTO>> createContentActivity(@Validated @RequestBody CreateContentActivityRequest createWishlistRequest) {
        return null;
    }

    @PreAuthorize("hasAuthority('APP_USER') or hasAuthority('ADMIN')")
    @GetMapping("")
    public ResponseEntity<GenericResponse<List<ContentActivityDTO>>> getContentActivities(@RequestParam(defaultValue = "0") Integer pageNo,
                                                                                          @RequestParam(defaultValue = "10") Integer pageSize,
                                                                                          @RequestParam ContentType contentType) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userContentActivityService.getUserContentActivitiesByPagination(user, contentType, pageNo, pageSize);
        return null;
    }

    @PreAuthorize("hasAuthority('APP_USER') or hasAuthority('ADMIN')")
    @PostMapping("")
    public ResponseEntity<GenericResponse<List<ContentActivityDTO>>> deleteContentActivity(@RequestParam Long id) {
        return null;
    }
}
