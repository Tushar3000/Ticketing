package com.tushar.ticket.domain.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.tushar.ticket.domain.entities.EventStatusEnum;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventRequestDto {
    @NotBlank(message="Event name is required")
    private String name;
    private LocalDateTime start;
    private LocalDateTime end;
    @NotBlank(message="Venue information is required")
    private String venue;
    private LocalDateTime salesStart;
    private LocalDateTime salesEnd;
    @NotNull(message="Event status must be provided")
    private EventStatusEnum status;
    @Valid
    private List<CreateTicketTypeRequestDto> ticketTypes;
}
