package com.enesd.myshelfbackend.services;

import com.enesd.myshelfbackend.dto.BookDTO;
import com.enesd.myshelfbackend.dto.CollectionBookDTO;
import com.enesd.myshelfbackend.dto.CollectionDTO;
import com.enesd.myshelfbackend.model.entities.*;
import com.enesd.myshelfbackend.model.request.AddContentToCollectionRequest;
import com.enesd.myshelfbackend.model.request.CreateCollectionRequest;
import com.enesd.myshelfbackend.repository.jpa.*;
import com.enesd.myshelfbackend.utils.CustomModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CollectionService {
    private final CollectionRepository collectionRepository;
    private final CollectionBookRepository collectionBookRepository;
    private final CollectionMediaContentRepository collectionMediaContentRepository;
    private final BookEntityRepository bookEntityRepository;
    private final MediaContentEntityRepository mediaContentEntityRepository;

    private final CustomModelMapper modelMapper;

    public CollectionDTO createCollection(User user, CreateCollectionRequest createCollectionRequest) {
        Collection collection = new Collection();
        collection.setName(createCollectionRequest.getName());
        collection.setUser(user);
        collection.setContentType(createCollectionRequest.getContentType());
        collectionRepository.save(collection);
        return modelMapper.map(collection, CollectionDTO.class);
    }

    public List<CollectionDTO> getCollectionsOfUser(User user) {
        List<Collection> collections = collectionRepository.findAllByUser(user);
        return modelMapper.mapAll(collections, CollectionDTO.class);
    }

    public List<BookDTO> getBooksOfCollectionById(Long collectionId) {
        Collection collection = collectionRepository.getReferenceById(collectionId);
        List<CollectionBook> collectionBooks = collectionBookRepository.findAllByCollection(collection);
        return collectionBooks
                .stream()
                .map(collectionBook -> modelMapper.map(collectionBook.getBook(), BookDTO.class))
                .collect(Collectors.toList());
    }

    public CollectionBookDTO addBookToCollection(Long collectionId, AddContentToCollectionRequest addContentToCollectionRequest) {
        Collection collection = collectionRepository.getReferenceById(collectionId);
        BookEntity book = bookEntityRepository.getReferenceById(addContentToCollectionRequest.getContentId());
        CollectionBook collectionBook = new CollectionBook(collection, book);
        collectionBookRepository.save(collectionBook);
        return modelMapper.map(collectionBook, CollectionBookDTO.class);
    }
}