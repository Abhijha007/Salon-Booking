package com.project.dto;
import lombok.Data;

import java.time.LocalDateTime;

public class BookingSlotDTO {

@Data
public class BookingSlotDTO {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}

