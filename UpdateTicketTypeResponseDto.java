package com.tushar.ticket.domain.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTicketTypeResponseDto {
    private UUID id;

   
    private String name;

    
    private Double price;


    private Integer totalAvailable;


    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
