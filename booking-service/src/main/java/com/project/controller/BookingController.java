package com.project.controller;

import com.project.dto.BookingRequest;
import com.project.dto.SalonDTO;
import com.project.dto.UserDTO;
import com.project.model.Booking;
import com.project.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

  private final BookingService bookingService;

  public ResponseEntity<Booking> createBooking(
            @RequestParam Long salonId,
            @RequestBody BookingRequest bookingRequest
            ){
    UserDTO user = new UserDTO();
        user.setId(1L);

    SalonDTO salon = new SalonDTO();
    salon.setId(salonId);

    Set<ServiceDTO> serviceDTOSet = new HashSet<>();

        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setId(1L);
        serviceDTO.setPrice(399);
        serviceDTO.setDuration(45);
        serviceDTO.setName("hair cut for men");

    serviceDTOSet.add(serviceDTO);

    Booking booking = bookingService.createBooking(bookingRequest,
                user,
                salon,
                serviceDTOSet);

    return null;
    }
}














