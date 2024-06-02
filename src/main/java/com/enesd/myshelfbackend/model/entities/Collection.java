package com.enesd.myshelfbackend.model.entities;

import com.enesd.myshelfbackend.enums.ContentType;
import com.enesd.myshelfbackend.model.abstracts.Auditable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "user_collections")
public class Collection extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "content_type", nullable = false)
    private ContentType contentType;

    @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL)
    private Set<CollectionBook> collectionBooks = new HashSet<>();

    @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL)
    private Set<CollectionMediaContent> collectionMediaContents = new HashSet<>();
}
