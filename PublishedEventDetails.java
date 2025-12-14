// src/main/java/com/tushar/ticket/domain/dtos/PublishedEventDetails.java
package com.tushar.ticket.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublishedEventDetails {
    private String id;
    private String name;
    private String description;
    private String venue;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    //private List<PublishedEventTicketTypeDetails> ticketTypes;
}