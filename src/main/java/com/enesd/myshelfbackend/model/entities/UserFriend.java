package com.enesd.myshelfbackend.model.entities;

import com.enesd.myshelfbackend.model.compositeKeys.UserBookWishlistId;
import com.enesd.myshelfbackend.model.compositeKeys.UserFriendId;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@IdClass(UserFriendId.class)
@Table(name = "user_friends")
public class UserFriend {

    @Id
    @Column(name = "user_id")
    private UUID userId;

    @Id
    @Column(name = "friend_id")
    private UUID friendId;

    @CreationTimestamp(source = SourceType.VM)
    @Column(name = "created_at")
    private Instant createdAt;
}
