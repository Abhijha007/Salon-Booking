package com.project.service.offering.controller;

import com.project.service.offering.model.ServiceOffering;
import com.project.service.offering.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/service-offering")
public class ServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<Set<ServiceOffering>> getServicesBySalonId(
            @PathVariable Long salonId,
            @RequestParam(required = false) Long categoryId
    ){
        Set<ServiceOffering> serviceOfferings=serviceOfferingService
                .getAllServiceBySalonId(salonId, categoryId);
        return ResponseEntity.ok(serviceOfferings);

    }


}
