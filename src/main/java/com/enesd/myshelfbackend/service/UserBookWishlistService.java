package com.enesd.myshelfbackend.service;

import com.enesd.myshelfbackend.model.compositeKeys.UserBookWishlistId;
import com.enesd.myshelfbackend.model.entities.Book;
import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.entities.UserBookWishlist;
import com.enesd.myshelfbackend.repository.BookRepository;
import com.enesd.myshelfbackend.repository.UserBookWishlistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserBookWishlistService {
    private final BookRepository bookRepository;
    private final UserBookWishlistRepository userBookWishlistRepository;

    public void addBookToUserWishlist(User user, int bookId) {
        Book book = bookRepository.getReferenceById(bookId);

        UserBookWishlistId userBookWishlistId = new UserBookWishlistId();
        userBookWishlistId.setUserId(user.getId());
        userBookWishlistId.setBookId(bookId);

        UserBookWishlist userBookWishlist = new UserBookWishlist();
        userBookWishlist.setUserBookWishlistId(userBookWishlistId);
        userBookWishlistRepository.save(userBookWishlist);
    }
}
