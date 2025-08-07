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
            }
}





