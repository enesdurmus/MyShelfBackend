package com.enesd.myshelfbackend.controller;

import com.enesd.myshelfbackend.model.documents.MediaContentDocument;
import com.enesd.myshelfbackend.model.entities.MediaEntity;
import com.enesd.myshelfbackend.model.response.GenericResponse;
import com.enesd.myshelfbackend.services.MediaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/media")
@AllArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    @GetMapping("/search")
    public ResponseEntity<GenericResponse<List<MediaContentDocument>>> searchRelatedMediaContents(@RequestParam String searchTerm) {
        return ResponseEntity.ok(GenericResponse.success(mediaService.searchRelatedMediaContents(searchTerm)));
    }

    @GetMapping("")
    public ResponseEntity<GenericResponse<MediaEntity>> findMediaContentById(@RequestParam(required = true) Long mediaContentId) {
        return ResponseEntity.ok(GenericResponse.success(mediaService.findMediaContentById(mediaContentId)));
    }
}
