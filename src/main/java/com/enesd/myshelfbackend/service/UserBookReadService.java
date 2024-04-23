package com.enesd.myshelfbackend.service;

import com.enesd.myshelfbackend.model.compositeKeys.UserBookReadId;
import com.enesd.myshelfbackend.model.compositeKeys.UserBookWishlistId;
import com.enesd.myshelfbackend.model.entities.Book;
import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.entities.UserBookRead;
import com.enesd.myshelfbackend.model.entities.UserBookWishlist;
import com.enesd.myshelfbackend.repository.BookRepository;
import com.enesd.myshelfbackend.repository.UserBookReadRepository;
import com.enesd.myshelfbackend.repository.UserBookWishlistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserBookReadService {

    private final BookRepository bookRepository;
    private final UserBookReadRepository userBookReadRepositoryr;

    public void addBookToUserReads(User user, int bookId) {
        Book book = bookRepository.getReferenceById(bookId);

        UserBookReadId userBookReadId = new UserBookReadId();
        userBookReadId.setUserId(user.getId());
        userBookReadId.setBookId(bookId);

        UserBookRead userBookRead = new UserBookRead();
        userBookRead.setUserBookReadId(userBookReadId);
        userBookReadRepositoryr.save(userBookRead);
    }
}
