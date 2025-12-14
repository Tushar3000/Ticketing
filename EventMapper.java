package com.tushar.ticket.mappers;



import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


import com.tushar.ticket.domain.CreateEventRequest;
import com.tushar.ticket.domain.CreateTicketTypeRequest;
import com.tushar.ticket.domain.UpdateEventRequest;
import com.tushar.ticket.domain.UpdateTicketTypeRequest;
import com.tushar.ticket.domain.dtos.CreateEventRequestDto;
import com.tushar.ticket.domain.dtos.CreateEventResponseDto;
import com.tushar.ticket.domain.dtos.CreateTicketTypeRequestDto;
import com.tushar.ticket.domain.dtos.CreateTicketTypeResponseDto;
import com.tushar.ticket.domain.dtos.GetEventDetailsResponseDto;
import com.tushar.ticket.domain.dtos.GetEventDetailsTicketTypesResponseDto;
import com.tushar.ticket.domain.dtos.ListEventResponseDto;
import com.tushar.ticket.domain.dtos.ListEventTicketTypeResponseDto;
import com.tushar.ticket.domain.dtos.PublishedEventDetails;
import com.tushar.ticket.domain.dtos.UpdateEventRequestDto;
import com.tushar.ticket.domain.dtos.UpdateEventResponseDto;
import com.tushar.ticket.domain.dtos.UpdateTicketTypeRequestDto;
import com.tushar.ticket.domain.dtos.UpdateTicketTypeResponseDto;
import com.tushar.ticket.domain.dtos.ListEventPublishedResponseDto;
import com.tushar.ticket.domain.entities.Event;
import com.tushar.ticket.domain.entities.TicketType;

@Mapper(componentModel = "spring",unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EventMapper {
    
    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);
    CreateEventRequest fromDto(CreateEventRequestDto dto);
    CreateEventResponseDto toDto(Event event);
    ListEventTicketTypeResponseDto toDto(TicketType ticketType);
    ListEventResponseDto toListEventResponseDto(Event event);
    GetEventDetailsTicketTypesResponseDto toGetEventDetailsTicketTypesResponseDto(TicketType ticketType);
   GetEventDetailsResponseDto toGetEventDetailsResponseDto(Event event);
   UpdateTicketTypeRequest fromDto(UpdateTicketTypeRequestDto dto);
   UpdateEventRequest fromDto(UpdateEventRequestDto dto);
   UpdateTicketTypeResponseDto toUpdateTicketTypeResponseDto(TicketType ticketType);
   UpdateEventResponseDto toUpdateEventResponseDto(Event event);
   ListEventPublishedResponseDto toListEventPublishedResponseDto(Event event);
   PublishedEventDetails toPublishedEventDetails(Event event);
}



