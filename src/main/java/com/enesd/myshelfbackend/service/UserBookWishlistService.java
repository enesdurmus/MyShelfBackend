package com.enesd.myshelfbackend.service;

import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.entities.UserBookWishlist;
import com.enesd.myshelfbackend.repository.jpa.UserBookWishlistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserBookWishlistService {
    private final UserBookWishlistRepository userBookWishlistRepository;

    public void addBookToUserWishlist(User user, int bookId) {
        UserBookWishlist userBookWishlist = new UserBookWishlist();
        userBookWishlist.setUserId(user.getId());
        userBookWishlist.setBookId(bookId);
        userBookWishlistRepository.save(userBookWishlist);
    }

    public List<UserBookWishlist> getUserReadBooks(User user) {
        return userBookWishlistRepository.findAllByUserId(user.getId());
    }
}
