package com.project.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private long salonId;

    private Long customerId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

   @ElementCollection   //to create the separate table in the database
    private Set<Long> serviceIds;

   private int totalPrice;
}
