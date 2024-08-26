package com.enesd.myshelfbackend.services;

import com.enesd.myshelfbackend.repository.jpa.CollectionContentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CollectionContentService {
    private final CollectionContentRepository collectionContentRepository;
}
