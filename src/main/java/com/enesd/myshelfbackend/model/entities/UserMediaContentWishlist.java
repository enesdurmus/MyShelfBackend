package com.enesd.myshelfbackend.model.entities;

import com.enesd.myshelfbackend.model.abstracts.Auditable;
import com.enesd.myshelfbackend.model.compositeKeys.UserMediaContentWishlistId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

@Entity
@Data
@IdClass(UserMediaContentWishlistId.class)
@Table(name = "user_media_contents_wishlists")
@AllArgsConstructor
@NoArgsConstructor
public class UserMediaContentWishlist extends Auditable implements Persistable<UserMediaContentWishlistId> {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_content_id")
    private MediaContentEntity mediaContent;

    @Override
    public UserMediaContentWishlistId getId() {
        return new UserMediaContentWishlistId(user.getId(), mediaContent.getId());
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
