package com.enesd.myshelfbackend.services;

import com.enesd.myshelfbackend.dto.BookDTO;
import com.enesd.myshelfbackend.dto.CollectionBookDTO;
import com.enesd.myshelfbackend.model.entities.BookEntity;
import com.enesd.myshelfbackend.model.entities.Collection;
import com.enesd.myshelfbackend.model.entities.CollectionBook;
import com.enesd.myshelfbackend.model.request.AddContentToCollectionRequest;
import com.enesd.myshelfbackend.repository.jpa.*;
import com.enesd.myshelfbackend.utils.CustomModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CollectionBookService {
    private final CollectionRepository collectionRepository;
    private final CollectionBookRepository collectionBookRepository;
    private final BookEntityRepository bookEntityRepository;
    private final CustomModelMapper modelMapper;

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