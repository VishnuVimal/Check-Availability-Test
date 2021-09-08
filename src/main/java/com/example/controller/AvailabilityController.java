package com.example.controller;

import com.example.dto.AvailabilityRequest;
import com.example.repository.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AvailabilityController {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @PostMapping("/api/checkAvailability")
    public Boolean isAvailable(@RequestBody AvailabilityRequest request) {
        Integer availableCount = availabilityRepository.getAvailabilityCheck(request.getCityName(), request.getProductName());
        return availableCount > 0;
    }
}