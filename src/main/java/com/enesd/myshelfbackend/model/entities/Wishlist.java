package com.enesd.myshelfbackend.model.entities;

import com.enesd.myshelfbackend.enums.ContentType;
import com.enesd.myshelfbackend.model.abstracts.Auditable;
import com.enesd.myshelfbackend.model.compositeKeys.WishlistId;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@IdClass(WishlistId.class)
@Table(name = "wishlists")
public class Wishlist extends Auditable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Id
    @Column(name = "content_id")
    private int contentId;

    @Id
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "content_type")
    private ContentType contentType;
}
