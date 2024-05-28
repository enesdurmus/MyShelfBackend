package com.enesd.myshelfbackend.repository.jpa;

import com.enesd.myshelfbackend.model.compositeKeys.UserContentActivityId;
import com.enesd.myshelfbackend.model.entities.UserContentActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContentActivityRepository extends JpaRepository<UserContentActivity, UserContentActivityId> {
}
