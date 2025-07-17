package com.project.service;

import com.project.domain.BookingStatus;
import com.project.dto.BookingRequest;
import com.project.dto.SalonDTO;
import com.project.dto.ServiceDTO;
import com.project.dto.UserDTO;
import com.project.model.Booking;
import com.project.model.SalonReport;

import java.time.LocalDate;
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
   SalonReport getSalonReport(Long salonId);
}
