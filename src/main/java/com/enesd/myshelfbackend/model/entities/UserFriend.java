package com.enesd.myshelfbackend.model.entities;

import com.enesd.myshelfbackend.model.compositeKeys.UserFriendId;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.time.Instant;

@Entity
@Data
@Table(name = "user_friends")
public class UserFriend {

    @EmbeddedId
    private UserFriendId userFriendId;

    @CreationTimestamp(source = SourceType.VM)
    @Column(name = "created_at")
    private Instant createdAt;
}
