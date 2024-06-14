package com.enesd.myshelfbackend.services;

import com.enesd.myshelfbackend.consts.CacheNames;
import com.enesd.myshelfbackend.dto.SubscriptionDTO;
import com.enesd.myshelfbackend.model.entities.Subscription;
import com.enesd.myshelfbackend.model.request.CreateSubscriptionRequest;
import com.enesd.myshelfbackend.repository.jpa.SubscriptionRepository;
import com.enesd.myshelfbackend.utils.CustomModelMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final CustomModelMapper modelMapper;
    private final SubscriptionService subscriptionService;

    @Cacheable(value = CacheNames.SUBSCRIPTIONS)
    public List<SubscriptionDTO> getSubscriptions() {
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        return modelMapper.mapAll(subscriptions, SubscriptionDTO.class);
    }

    public SubscriptionDTO getSubscription(Long subscriptionId) {
        Optional<SubscriptionDTO> subscription = subscriptionService.getSubscriptions().stream().filter(subscriptionDTO -> subscriptionDTO.getId() == subscriptionId).findFirst();
        if (subscription.isPresent()) {
            return modelMapper.map(subscription.get(), SubscriptionDTO.class);
        }
        throw new EntityNotFoundException("");
    }

    @CacheEvict(value = CacheNames.SUBSCRIPTIONS, allEntries = true)
    public SubscriptionDTO createSubscription(CreateSubscriptionRequest createSubscriptionRequest) {
        Subscription subscription = new Subscription();
        subscription.setName(createSubscriptionRequest.getName());
        subscription.setAmount(createSubscriptionRequest.getAmount());
        subscription.setPrice(createSubscriptionRequest.getPrice());
        subscription.setSubscriptionType(createSubscriptionRequest.getSubscriptionType());
        subscription.setDurationHours(createSubscriptionRequest.getDurationHours());
        subscriptionRepository.save(subscription);
        return modelMapper.map(subscription, SubscriptionDTO.class);
    }

    @CacheEvict(value = CacheNames.SUBSCRIPTIONS, allEntries = true)
    public SubscriptionDTO updateSubscription(CreateSubscriptionRequest createSubscriptionRequest) {
        return null;
    }

    @CacheEvict(value = CacheNames.SUBSCRIPTIONS, allEntries = true)
    public void deleteSubscription(Long subscriptionId) {
        subscriptionRepository.delete(subscriptionRepository.getReferenceById(subscriptionId));
    }
}
