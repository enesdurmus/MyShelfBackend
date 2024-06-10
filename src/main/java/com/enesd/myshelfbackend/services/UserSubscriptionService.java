package com.enesd.myshelfbackend.services;

import com.enesd.myshelfbackend.consts.CacheNames;
import com.enesd.myshelfbackend.dto.UserSubscriptionDTO;
import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.entities.UserSubscription;
import com.enesd.myshelfbackend.repository.jpa.UserSubscriptionRepository;
import com.enesd.myshelfbackend.utils.CustomModelMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserSubscriptionService {

    private final UserSubscriptionRepository userSubscriptionRepository;
    private final CustomModelMapper modelMapper;

    @Cacheable(value = CacheNames.USER_SUBSCRIPTIONS, key = "#user.id")
    public List<UserSubscriptionDTO> getCachedUserSubscriptions(User user) {
        List<UserSubscription> userSubscriptions = userSubscriptionRepository.findByUser(user).orElseThrow(EntityNotFoundException::new);
        return modelMapper.mapAll(userSubscriptions, UserSubscriptionDTO.class);
    }
}