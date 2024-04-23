package com.enesd.myshelfbackend.service;

import com.enesd.myshelfbackend.model.compositeKeys.UserBookWishlistId;
import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.entities.UserBookWishlist;
import com.enesd.myshelfbackend.repository.UserBookWishlistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserBookWishlistService {
    private final UserBookWishlistRepository userBookWishlistRepository;

    public void addBookToUserWishlist(User user, int bookId) {
        UserBookWishlistId userBookWishlistId = new UserBookWishlistId();
        userBookWishlistId.setUserId(user.getId());
        userBookWishlistId.setBookId(bookId);

        UserBookWishlist userBookWishlist = new UserBookWishlist();
        userBookWishlist.setUserBookWishlistId(userBookWishlistId);
        userBookWishlistRepository.save(userBookWishlist);
    }

    public List<UserBookWishlist> getUserReadBooks(User user) {
        return userBookWishlistRepository.findAllByIdUserId(user.getId());
    }
}
