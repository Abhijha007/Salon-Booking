package com.project.controller;

import com.project.dto.*;
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
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    return ResponseEntity.ok(booking);
    }

  @GetMapping("/customer")
  public ResponseEntity<Set<BookingDTO>> getBookingByCustomer(
    
  ){
     List<Booking> bookings = bookingService.getBookingsByCustomer(1L);

             return ResponseEntity.ok(getBookingDTOs(bookings));
    }
  @GetMapping("/salon")
  public ResponseEntity<Set<BookingDTO>> getBookingsBySalon(){
        List<Booking> bookings=bookingService.getBookingsBySalon(1L);

        return ResponseEntity.ok(getBookingDTOs(bookings));
    }
  
  private Set<BookingDTO> getBookingDTOs(List<Booking> bookings){

        return bookings.stream()
                .map(booking -> {
                    return BookingMapper.toDTO(booking);
                }).collect(Collectors.toSet());
    }
  @GetMapping("/{bookingId}")
  public ResponseEntity<BookingDTO> getBookingById(
            @PathVariable Long bookingId
    ) throws Exception{
        Booking booking = bookingService.getBookingsById(bookingId);
        return ResponseEntity.ok(BookingMapper.toDTO(booking));
    }
  @PutMapping("/{bookingId}/status")
    public ResponseEntity<BookingDTO> updateBookingStatus(
            @PathVariable Long bookingId,
            @RequestParam BookingStatus status
            )throws Exception{
      Booking booking = bookingService.updateBooking(bookingId, status);
        return ResponseEntity.ok(BookingMapper.toDTO(booking));
            }
  @GetMapping("/slot/salon/{salonId}/date/{date}")
    public ResponseEntity<BookingDTO> getBookedSlot(
            @PathVariable Long salonId,
            @RequestParam LocalDate date
            )throws Exception{
      List<Booking> bookings = (List<Booking>) bookingService.getBookingByDate(date, salonId);

      List<BookingSlotDTO> slotsDTOs=bookings.stream()
                .map(booking ->{
                  BookingSlotDTO slotDTO = new BookingSlotDTO();
                  slotDTO.setStartTime(booking.getStartTime());
                  slotDTO.setEndTime(booking.getEndTime());
                  return slotDTO;
                }).collect(Collectors.toList());
      return ResponseEntity.ok(getBookingDTOs(booking));
            }
}













































