package com.enesd.myshelfbackend.services;

import com.enesd.myshelfbackend.dto.UserBookWishlistDTO;
import com.enesd.myshelfbackend.dto.UserMediaContentWishlistDTO;
import com.enesd.myshelfbackend.model.entities.*;
import com.enesd.myshelfbackend.repository.jpa.*;
import com.enesd.myshelfbackend.utils.CustomModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WishlistService {
    private final UserBookWishlistRepository userBookWishlistRepository;
    private final UserMediaContentWishlistRepository userMediaContentWishlistRepository;
    private final BookEntityRepository bookEntityRepository;
    private final MediaContentEntityRepository mediaContentEntityRepository;
    private final UserRepository userRepository;
    private final CustomModelMapper modelMapper;

    public void addBookToWishlist(User user, int bookId) {
        BookEntity bookEntity = bookEntityRepository.getReferenceById(bookId);
        User userRef = userRepository.getReferenceById(user.getId());
        UserBookWishlist userBookWishlist = new UserBookWishlist();
        userBookWishlist.setBook(bookEntity);
        userBookWishlist.setUser(userRef);
        userBookWishlistRepository.save(userBookWishlist);
    }

    public void addMediaContentToWishlist(User user, int mediaContentId) {
        MediaContentEntity mediaContentEntity = mediaContentEntityRepository.getReferenceById(mediaContentId);
        User userRef = userRepository.getReferenceById(user.getId());
        UserMediaContentWishlist userMediaContentWishlist = new UserMediaContentWishlist();
        userMediaContentWishlist.setMediaContent(mediaContentEntity);
        userMediaContentWishlist.setUser(userRef);
        userMediaContentWishlistRepository.save(userMediaContentWishlist);
    }

    public List<UserMediaContentWishlistDTO> getMediaContentWishlists(User user) {
        List<UserMediaContentWishlist> userMediaContentWishlists = userMediaContentWishlistRepository.findAllByUser(user);
        return modelMapper.mapAll(userMediaContentWishlists, UserMediaContentWishlistDTO.class);
    }

    public List<UserBookWishlistDTO> getBookWishlists(User user) {
        List<UserBookWishlist> userBookWishlists = userBookWishlistRepository.findAllByUser(user);
        return modelMapper.mapAll(userBookWishlists, UserBookWishlistDTO.class);
    }

//    @Transactional
//    public void deleteWishlist(Long id) {
//        wishlistRepository.deleteById(id);
//    }
}
