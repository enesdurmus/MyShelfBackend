package com.enesd.myshelfbackend.repository.jpa;

import com.enesd.myshelfbackend.model.compositeKeys.BookCollectionId;
import com.enesd.myshelfbackend.model.entities.Collection;
import com.enesd.myshelfbackend.model.entities.CollectionBook;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectionBookRepository extends JpaRepository<CollectionBook, BookCollectionId> {
    @EntityGraph(attributePaths = {"book"}, type = EntityGraph.EntityGraphType.FETCH)
    List<CollectionBook> findAllByCollection(Collection collection);
}
