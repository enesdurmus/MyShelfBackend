package com.enesd.myshelfbackend.services;

import com.enesd.myshelfbackend.dto.WishlistDTO;
import com.enesd.myshelfbackend.enums.ContentType;
import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.entities.Wishlist;
import com.enesd.myshelfbackend.model.request.CreateWishlistRequest;
import com.enesd.myshelfbackend.repository.jpa.UserRepository;
import com.enesd.myshelfbackend.repository.jpa.WishlistRepository;
import com.enesd.myshelfbackend.utils.CustomModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class WishlistService {
    private final WishlistRepository wishlistRepository;
    private final CustomModelMapper modelMapper;
    private final UserRepository userRepository;

    public WishlistDTO createWishlist(User user, CreateWishlistRequest createWishlistRequest) {
        User userRef = userRepository.getReferenceById(user.getId());//TODO Check "check detached entity passed to persist" exception
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(userRef);
        wishlist.setContentId(createWishlistRequest.getContentId());
        wishlist.setContentType(createWishlistRequest.getContentType());
        wishlistRepository.save(wishlist);
        return modelMapper.map(wishlist, WishlistDTO.class);
    }

    public List<WishlistDTO> getWishlists(User user, ContentType contentType) {
        List<Wishlist> wishlists = wishlistRepository.findAllByUserAndContentType(user, contentType);
        return modelMapper.mapAll(wishlists, WishlistDTO.class);
    }

    @Transactional
    public void deleteWishlist(Long id) {
        wishlistRepository.deleteById(id);
    }
}
