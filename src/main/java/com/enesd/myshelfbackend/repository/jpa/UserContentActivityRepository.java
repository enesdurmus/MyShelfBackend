package com.enesd.myshelfbackend.repository.jpa;

import com.enesd.myshelfbackend.enums.ContentType;
import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.entities.UserContentActivity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserContentActivityRepository extends JpaRepository<UserContentActivity, Long> {

    List<UserContentActivity> findAllByUserAndContentType(User userId, ContentType contentType, Pageable pageable);
}
