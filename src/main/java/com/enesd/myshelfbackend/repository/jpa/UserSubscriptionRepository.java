package com.enesd.myshelfbackend.repository.jpa;

import com.enesd.myshelfbackend.model.compositeKeys.UserSubscriptionId;
import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.entities.UserSubscription;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, UserSubscriptionId> {
    @EntityGraph(attributePaths = {"subscription"}, type = EntityGraph.EntityGraphType.FETCH)
    Optional<List<UserSubscription>> findByUser(User user);
}
