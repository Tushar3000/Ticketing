package com.tushar.ticket.domain.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.tushar.ticket.domain.entities.Event;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTicketTypeResponseDto {
     private UUID id;

   
    private String name;

    
    private Double price;


    private Integer totalAvailable;


    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    
    
    

}
