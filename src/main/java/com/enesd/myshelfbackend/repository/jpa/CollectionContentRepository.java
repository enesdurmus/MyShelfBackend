package com.enesd.myshelfbackend.repository.jpa;

import com.enesd.myshelfbackend.model.abstracts.Book;
import com.enesd.myshelfbackend.model.compositeKeys.CollectionContentId;
import com.enesd.myshelfbackend.model.entities.CollectionContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CollectionContentRepository extends JpaRepository<CollectionContent, CollectionContentId> {
    @Query("SELECT book FROM CollectionContent collectionContent " +
            "INNER JOIN BookEntity book ON book.id = collectionContent.content " +
            "WHERE collectionContent.collection.id = :collectionId")
    List<Book> findAllBooksByCollectionId(@Param("collectionId") Long collectionId);
}
