package com.enesd.myshelfbackend.model.entities;

import com.enesd.myshelfbackend.model.abstracts.Auditable;
import com.enesd.myshelfbackend.model.compositeKeys.UserFriendId;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@IdClass(UserFriendId.class)
@Table(name = "friendships")
public class Friendship extends Auditable {

    @Id
    @Column(name = "user_id")
    private UUID userId;

    @Id
    @Column(name = "friend_id")
    private UUID friendId;
}
