package com.project.service;

import com.project.dto.BookingRequest;
import com.project.dto.SalonDTO;
import com.project.dto.ServiceDTO;
import com.project.dto.UserDTO;
import com.project.model.Booking;


public interface BookingService {
   Booking createBooking(BookingRequest booking, UserDTO user,
                          SalonDTO salon, Set<ServiceDTO> serviceDTOSet);
}
