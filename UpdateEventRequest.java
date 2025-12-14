package com.tushar.ticket.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.tushar.ticket.domain.entities.EventStatusEnum;
import com.tushar.ticket.domain.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UpdateEventRequest {
    private UUID id;
    private String name;
    private LocalDateTime start;
    private LocalDateTime end;
    private String venue;
    private LocalDateTime salesStart;
    private LocalDateTime salesEnd;
    private EventStatusEnum status;

    private List<UpdateTicketTypeRequest> ticketTypes=new ArrayList<>();
    
}
