package com.enesd.myshelfbackend.model.entities;

import com.enesd.myshelfbackend.enums.ContentType;
import com.enesd.myshelfbackend.model.abstracts.Content;
import com.enesd.myshelfbackend.model.compositeKeys.CollectionContentId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@IdClass(CollectionContentId.class)
@Table(name = "collections_contents")
@AllArgsConstructor
@NoArgsConstructor
public class CollectionContent {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id")
    private Collection collection;

    @Id
    @Column(name = "content_id")
    private Long content;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "content_type", nullable = false)
    private ContentType contentType;
}
