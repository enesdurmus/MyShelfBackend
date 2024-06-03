package com.enesd.myshelfbackend.model.entities;

import com.enesd.myshelfbackend.model.abstracts.Auditable;
import com.enesd.myshelfbackend.model.compositeKeys.BookCollectionId;
import com.enesd.myshelfbackend.model.compositeKeys.MediaContentCollectionId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

@Data
@Entity
@IdClass(MediaContentCollectionId.class)
@Table(name = "collections_media_contents")
@AllArgsConstructor
@NoArgsConstructor
public class CollectionMediaContent extends Auditable implements Persistable<BookCollectionId> {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id")
    private Collection collection;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_content_id")
    private MediaContentEntity mediaContent;

    @Override
    public BookCollectionId getId() {
        return new BookCollectionId(collection.getId(), mediaContent.getId());
    }

    @Override
    public boolean isNew() {
        return true;
    }
}