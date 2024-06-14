package com.enesd.myshelfbackend.controller;

import com.enesd.myshelfbackend.dto.SubscriptionDTO;
import com.enesd.myshelfbackend.model.request.CreateSubscriptionRequest;
import com.enesd.myshelfbackend.model.response.GenericResponse;
import com.enesd.myshelfbackend.services.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subscriptions")
@AllArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{subscriptionId}")
    public ResponseEntity<GenericResponse<SubscriptionDTO>> getSubscription(@PathVariable Long subscriptionId) {
        return ResponseEntity.ok(GenericResponse.success(subscriptionService.getSubscription(subscriptionId)));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<GenericResponse<List<SubscriptionDTO>>> getSubscriptions() {
        return ResponseEntity.ok(GenericResponse.success(subscriptionService.getSubscriptions()));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<GenericResponse<SubscriptionDTO>> createSubscription(@RequestBody @Validated CreateSubscriptionRequest createSubscriptionRequest) {
        return ResponseEntity.ok(GenericResponse.success(subscriptionService.createSubscription(createSubscriptionRequest)));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{subscriptionId}")
    public ResponseEntity<GenericResponse<SubscriptionDTO>> updateSubscription(@RequestBody @Validated CreateSubscriptionRequest createSubscriptionRequest) {
        return ResponseEntity.ok(GenericResponse.success(subscriptionService.updateSubscription(createSubscriptionRequest)));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{subscriptionId}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Long subscriptionId) {
        subscriptionService.deleteSubscription(subscriptionId);
        return ResponseEntity.noContent().build();
    }
}
