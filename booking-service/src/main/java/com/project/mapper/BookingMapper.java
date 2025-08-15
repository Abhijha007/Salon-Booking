package com.project.mapper;

import com.project.dto.BookingDTO;
import com.project.model.Booking;

public class BookingMapper {
  public static BookingDTO toDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setCustomerId(booking.getCustomerId());
        bookingDTO.setStatus(booking.getStatus());
        bookingDTO.setEndTime(booking.getEndTime());
        bookingDTO.setSalonId(bookingDTO.getSalonId());
        bookingDTO.setServiceIds(booking.getServiceIds());
        return bookingDTO;
    }
}


