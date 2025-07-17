package com.project.service;

import com.project.dto.BookingRequest;
import com.project.dto.SalonDTO;
import com.project.dto.ServiceDTO;
import com.project.dto.UserDTO;
import com.project.model.Booking;

import java.util.List;
import java.util.Set;

public interface BookingService {
   Booking createBooking(BookingRequest booking, UserDTO user,
                          SalonDTO salon, Set<ServiceDTO> serviceDTOSet);

   List<Booking> getBookingsByCustomer(Long customerId);
   List<Booking> getBookingsBySalon(Long salonId);
   Booking getBookingById(Long id);
   Booking updateBooking(Long bookingId, BookingStatus status );
   List<Booking> getBookingByDate(LocalDate date, Long salonId);
}
