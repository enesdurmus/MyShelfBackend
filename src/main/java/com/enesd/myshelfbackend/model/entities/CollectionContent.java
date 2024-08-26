package com.enesd.myshelfbackend.model.entities;

import com.enesd.myshelfbackend.model.abstracts.Auditable;
import com.enesd.myshelfbackend.model.compositeKeys.CollectionContentId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

@Data
@Entity
@IdClass(CollectionContentId.class)
@Table(name = "collections_contents")
@AllArgsConstructor
@NoArgsConstructor
public class CollectionContent extends Auditable implements Persistable<CollectionContentId> {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id")
    private Collection collection;

    @Id
    @Column(name = "content_id")
    private Long content;

    @Override
    public CollectionContentId getId() {
        return new CollectionContentId(collection.getId(), content);
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
