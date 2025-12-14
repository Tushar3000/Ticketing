package com.tushar.ticket.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tushar.ticket.domain.CreateEventRequest;
import com.tushar.ticket.domain.UpdateEventRequest;
import com.tushar.ticket.domain.dtos.PublishedEventDetails;
import com.tushar.ticket.domain.entities.Event;

public interface EventService {
    Event createEvent(UUID organizerId,CreateEventRequest event);
   //public List<Event> getAllEvents();
    Page<Event> listEventsForOrganizer(UUID organizerId,Pageable pageable);
    Optional<Event> getEventForOrganizer (UUID organizerId, UUID id);
    Event updateEventForOrganizer(UUID organizerId,UUID id,UpdateEventRequest event);
    void deleteEventForOrganizer(UUID organizerId,UUID id);
    Page<Event> listPublishedEvents(Pageable pageable);
    PublishedEventDetails getPublishedEventDetails(UUID id);
}
