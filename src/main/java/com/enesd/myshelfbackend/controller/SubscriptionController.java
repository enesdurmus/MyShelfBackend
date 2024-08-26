package com.enesd.myshelfbackend.controller;

import com.enesd.myshelfbackend.dto.SubscriptionDTO;
import com.enesd.myshelfbackend.enums.Role;
import com.enesd.myshelfbackend.model.request.CreateSubscriptionRequest;
import com.enesd.myshelfbackend.model.response.GenericResponse;
import com.enesd.myshelfbackend.services.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subscriptions")
@AllArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @GetMapping("/{subscriptionId}")
    public ResponseEntity<GenericResponse<SubscriptionDTO>> getSubscription(@PathVariable Long subscriptionId) {
        return ResponseEntity.ok(GenericResponse.success(subscriptionService.getSubscription(subscriptionId)));
    }

    @GetMapping
    public ResponseEntity<GenericResponse<List<SubscriptionDTO>>> getSubscriptions() {
        return ResponseEntity.ok(GenericResponse.success(subscriptionService.getSubscriptions()));
    }

    @Secured(value = {Role.Code.ADMIN})
    @PostMapping
    public ResponseEntity<GenericResponse<SubscriptionDTO>> createSubscription(@RequestBody @Validated CreateSubscriptionRequest createSubscriptionRequest) {
        return ResponseEntity.ok(GenericResponse.success(subscriptionService.createSubscription(createSubscriptionRequest)));
    }

    @Secured(value = {Role.Code.ADMIN})
    @PutMapping("/{subscriptionId}")
    public ResponseEntity<GenericResponse<SubscriptionDTO>> updateSubscription(@RequestBody @Validated CreateSubscriptionRequest createSubscriptionRequest) {
        return ResponseEntity.ok(GenericResponse.success(subscriptionService.updateSubscription(createSubscriptionRequest)));
    }

    @Secured(value = {Role.Code.ADMIN})
    @DeleteMapping("/{subscriptionId}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Long subscriptionId) {
        subscriptionService.deleteSubscription(subscriptionId);
        return ResponseEntity.noContent().build();
    }
}
