package com.enesd.myshelfbackend.repository.jpa;

import com.enesd.myshelfbackend.model.compositeKeys.UserFriendId;
import com.enesd.myshelfbackend.model.entities.UserFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserFriendRepository extends JpaRepository<UserFriend, UserFriendId> {

    @Query("SELECT uf FROM UserFriend uf WHERE uf.userId = :userId OR uf.friendId = :userId")
    public List<UserFriend> findAllByUserIdOrFriendId(@Param("userId") UUID userId);
}
