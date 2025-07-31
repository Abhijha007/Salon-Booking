package com.project.service.impl;

import com.project.domain.BookingStatus;
import com.project.dto.BookingRequest;
import com.project.dto.SalonDTO;
import com.project.dto.ServiceDTO;
import com.project.dto.UserDTO;
import com.project.model.Booking;
import com.project.model.SalonReport;
import com.project.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService{

  private final BookingRepository bookingRepository;

  @Override
    public Booking createBooking(BookingRequest booking, 
                                 UserDTO user, 
                                 SalonDTO salon, 
                                 Set<ServiceDTO> serviceDTOSet) {
    int totalDuration = serviceDTOSet.stream()
                        .mapToInt(ServiceDTO::getDuration)
                        .sum();
    LocalDateTime bookingStartTime = booking.getStartTime();
    LocalDateTime bookingEndTime = bookingStartTime.plusMinutes(totalDuration);

    Boolean isSlotAvailable = isTimeSlotAvailable(salon,bookingStartTime,bookingEndTime);

    int totalPrice = serviceDTOSet.stream()
                .mapToInt(ServiceDTO::getPrice)
                .sum();
      
    Set<Long> idList=serviceDTOSet.stream()
                .map(ServiceDTO::getId)
                .collect(Collectors.toSet());

      Booking newBooking=new Booking();
        newBooking.setCustomerId(user.getId());
        newBooking.setSalonId(salon.getId());
        newBooking.setServiceIds(idList);
        newBooking.setStatus(BookingStatus.PENDING);
        newBooking.setStartTime(bookingStartTime);
        newBooking.setEndTime(bookingEndTime);
        newBooking.setTotalPrice(totalPrice);
      
        return bookingRepository.save(newBooking);
    }

  public Boolean isTimeSlotAvailable(SalonDTO salonDTO,
                                       LocalDateTime bookingStartTime,
                                       LocalDateTime bookingEndTime) throws Exception{

    List<Booking> existingBookings= getBookingsBySalon(salonDTO.getId());

        LocalDateTime salonOpenTime = salonDTO.getOpenTime().atDate(bookingStartTime.toLocalDate());
        LocalDateTime salonCloseTime = salonDTO.getCloseTime().atDate(bookingEndTime.toLocalDate());
    
    if(bookingStartTime.isBefore(salonOpenTime) || bookingEndTime.isAfter(salonCloseTime)){
            throw new Exception("Booking time must be within salon's working hours");
        }

    for(Booking existingBooking: existingBookings){
            LocalDateTime existingBookingStartTime=existingBooking.getStartTime();
            LocalDateTime existingBookingEndTime = existingBooking.getEndTime();

      if(bookingStartTime.isBefore(existingBookingEndTime)
        && bookingEndTime.isAfter(existingBookingStartTime)){
        throw new Exception("Slot not available, choose different time.");
            }
      if(bookingStartTime.isEqual(existingBookingStartTime)
                    || bookingEndTime.isEqual(existingBookingEndTime)){
        throw new Exception("Slot not available, choose different time.");
                    }
        }
        return true;
    }

    @Override
    public List<Booking> getBookingsByCustomer(Long customerId) {
        return bookingRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Booking> getBookingsBySalon(Long salonId) {
        return bookingRepository.findBySalonId();
    }

    @Override
    public Booking getBookingById(Long id) throws Exception{
      Booking booking = bookingRepository.findById(id).orElse(null);
      if(booking == null){
            throw new Exception("Booking not found.");
        }
        return booking;
    }

    @Override
    public Booking updateBooking(Long bookingId, BookingStatus status) {
        return null;
    }

    @Override
    public List<Booking> getBookingByDate(LocalDate date, Long salonId) {
        return List.of();
    }

    @Override
    public SalonReport getSalonReport(Long salonId) {
        return null;
    }
}
