package com.enesd.myshelfbackend.repository.jpa;

import com.enesd.myshelfbackend.model.compositeKeys.UserFriendId;
import com.enesd.myshelfbackend.model.entities.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface FriendshipRepository extends JpaRepository<Friendship, UserFriendId> {

    @Query("SELECT fs FROM Friendship fs WHERE fs.userId = :userId OR fs.friendId = :userId")
    public List<Friendship> findAllByUserIdOrFriendId(@Param("userId") UUID userId);
}
