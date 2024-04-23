package com.enesd.myshelfbackend.service;

import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.entities.UserBookRead;
import com.enesd.myshelfbackend.repository.UserBookReadRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class UserBookReadService {

    private final UserBookReadRepository userBookReadRepository;

    public void addBookToUserReads(User user, int bookId) {
        UserBookRead userBookRead = new UserBookRead();
        userBookRead.setUserId(user.getId());
        userBookRead.setBookId(bookId);
        userBookRead.setCreatedAt(Instant.now());
        userBookReadRepository.save(userBookRead);
    }

    public List<UserBookRead> getUserReadBooks(User user) {
        return userBookReadRepository.findAllByUserId(user.getId());
    }
}
