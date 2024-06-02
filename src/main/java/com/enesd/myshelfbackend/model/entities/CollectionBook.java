package com.enesd.myshelfbackend.model.entities;

import com.enesd.myshelfbackend.model.abstracts.Auditable;
import com.enesd.myshelfbackend.model.compositeKeys.BookCollectionId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

@Data
@Entity
@IdClass(BookCollectionId.class)
@Table(name = "collections_books")
@AllArgsConstructor
@NoArgsConstructor
public class CollectionBook extends Auditable implements Persistable<BookCollectionId> {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id")
    private Collection collection;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @Override
    public BookCollectionId getId() {
        return new BookCollectionId(collection.getId(), book.getId());
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
