package com.enesd.myshelfbackend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/media_contents")
@AllArgsConstructor
public class MediaContentController {

    @GetMapping("/search")
    public ResponseEntity searchRelatedMediaContents(@RequestParam String searchTerm) {
        return null;
    }

    @GetMapping("")
    public ResponseEntity findMediaContentById(@RequestParam(required = true) int mediaContentId) {
        return null;
    }
}
