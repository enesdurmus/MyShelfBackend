package com.enesd.myshelfbackend.controller;

import com.enesd.myshelfbackend.model.response.GenericResponse;
import com.enesd.myshelfbackend.services.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/search")
@AllArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @GetMapping(value = "/all")
    public ResponseEntity<GenericResponse<Map<String, List<Object>>>> searchAllTypes(@RequestParam(value = "searchTerm") String searchTerm) {
        return ResponseEntity.ok(GenericResponse.success(searchService.searchAllTypes(searchTerm)));
    }
}
