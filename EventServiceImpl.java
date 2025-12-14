/*package com.tushar.ticket.services.impl;

import java.util.UUID;

import com.tushar.ticket.domain.CreateEventRequest;
import com.tushar.ticket.domain.entities.Event;
import com.tushar.ticket.domain.entities.TicketType;
import com.tushar.ticket.domain.exception.UserNotFoundException;
import com.tushar.ticket.repositories.UserRepository;
import com.tushar.ticket.services.EventService;

public class EventServiceImpl implements EventService{

    private final UserRepository userRepository;

    @Override
    public Event createEvent(UUID organizerId,CreateEventRequest event)
    {
        private final EventRepository eventRepository;
        User organizer=userRepository.findById(organizerId).orElseTrow(()->new UserNotFoundException(
            String.format("User with ID '%s' not found.",organizerId)));
            event.getTicketTypes().stream().map(
                ticketType->{
                    TicketType ticketTypeToCreate =new TicketType();
                    ticketTypeToCreate.setName(ticketType.getName());
                    ticketTypeToCreate.setPrice(ticketType.getPrice());
                    ticketTypeToCreate.setDescription(ticketType.getDescription());
                    ticketTypeToCreate.setTotalAvailable(ticketType.getTotalAvailable());
                   return ticketTypeToCreate;

                }
            ).toList();
            Event eventToCreate=new Event();
            eventToCreate.setName(event.getName());
            eventToCreate.setStart(event.getStart());
            eventToCreate.setEnd(event.getEnd());
            eventToCreate.setVenue(event.getVenue());
            eventToCreate.setSalesStart(event.getSalesStart());
            eventToCreate.setSalesEnd(event.getSalesEnd());
            eventToCreate.setStatus(event.getStatus());
            eventToCreate.setOrganizer(organizer);
            eventToCreate.setTicketTypes(ticketTypesToCreate);
            

        return eventRepository.save(eventToCreate);
    } 
    
}
*/
package com.tushar.ticket.services.impl;
import com.tushar.ticket.repositories.EventRepository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tushar.ticket.domain.CreateEventRequest;
import com.tushar.ticket.domain.UpdateEventRequest;
import com.tushar.ticket.domain.UpdateTicketTypeRequest;
import com.tushar.ticket.domain.entities.Event;
import com.tushar.ticket.domain.entities.EventStatusEnum;
import com.tushar.ticket.domain.entities.TicketType;
import com.tushar.ticket.domain.entities.User;
import com.tushar.ticket.domain.exception.EventNotFoundException;
import com.tushar.ticket.domain.exception.EventUpdateException;
import com.tushar.ticket.domain.exception.TicketTypeNotFoundException;
import com.tushar.ticket.domain.exception.UserNotFoundException;
import com.tushar.ticket.repositories.UserRepository;
import com.tushar.ticket.services.EventService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Override
    @Transactional
    public Event createEvent(UUID organizerId, CreateEventRequest event) {
        User organizer = userRepository.findById(organizerId)
            .orElseThrow(() -> new UserNotFoundException(
                String.format("User with ID '%s' not found.", organizerId)));
                Event eventToCreate = new Event();
                eventToCreate.setName(event.getName());
                eventToCreate.setStart(event.getStart());
                eventToCreate.setEnd(event.getEnd());
                eventToCreate.setVenue(event.getVenue());
                eventToCreate.setSalesStart(event.getSalesStart());
                eventToCreate.setSalesEnd(event.getSalesEnd());
                eventToCreate.setStatus(event.getStatus());
                eventToCreate.setOrganizer(organizer);

        List<TicketType> ticketTypesToCreate = event.getTicketTypes().stream()
        .map(ticketType -> {
            TicketType ticketTypeToCreate = new TicketType();
            ticketTypeToCreate.setName(ticketType.getName());
            ticketTypeToCreate.setPrice(ticketType.getPrice());
            ticketTypeToCreate.setDescription(ticketType.getDescription());
            ticketTypeToCreate.setTotalAvailable(ticketType.getTotalAvailable());
            ticketTypeToCreate.setEvent(eventToCreate);
            
            return ticketTypeToCreate;
        }).collect(Collectors.toList());

                
               
                eventToCreate.setTicketTypes(ticketTypesToCreate);

        return eventRepository.save(eventToCreate);
    }

    /*@Override
    public List<Event> getAllEvents() {
        // TODO Auto-generated method stub
        return eventRepository.findAll();
       // throw new UnsupportedOperationException("Unimplemented method 'getAllEvents'");
    }*/

    @Override
    public Page<Event> listEventsForOrganizer(UUID organizerId,Pageable pageable) {
        // TODO Auto-generated method stub
        return eventRepository.findByOrganizerId(organizerId,pageable);
    }

    @Override
    public Optional<Event> getEventForOrganizer(UUID organizerId, UUID id) {
        // TODO Auto-generated method stub
       return eventRepository.findByIdAndOrganizerId(id, organizerId);
    }

    @Override
    @Transactional
    public Event updateEventForOrganizer(UUID organizerId, UUID id, UpdateEventRequest event) {
        // TODO Auto-generated method stub
       if(null==event.getId())
            {
                throw new EventUpdateException("Event Id cannot be null");
            }
            if(!id.equals(event.getId()))
            {
                throw new EventUpdateException("Cannot update The ID of the event");
            }
            
        Event existingEvent=eventRepository.
            findByIdAndOrganizerId(id, organizerId)
            .orElseThrow(()->new EventNotFoundException(
           String.format("Event with id '%s' does not exist",id))
            );
            existingEvent.setName(event.getName());
            existingEvent.setStart(event.getStart());
            existingEvent.setVenue(event.getVenue());
            existingEvent.setEnd(event.getEnd());
            existingEvent.setSalesStart(event.getSalesStart());
            existingEvent.setSalesEnd(event.getSalesEnd());
            existingEvent.setStatus(event.getStatus());

            Set<UUID> requestTicketTypeIds=event.getTicketTypes()
            .stream()
            .map(UpdateTicketTypeRequest::getId)
            .filter(Objects::nonNull)
            .collect(Collectors.toSet());

            existingEvent.getTicketTypes().removeIf(existingTicketType->
                !requestTicketTypeIds.contains(existingTicketType.getId())
            );
            List<UpdateTicketTypeRequest> ticketTypes = event.getTicketTypes();

            if (ticketTypes == null) {
                // If the list is null, treat it as empty to avoid NPEs later in the stream logic
                ticketTypes = List.of(); 
            }
            Map<UUID,TicketType> existingTicketTypesIndex=existingEvent.getTicketTypes().stream()
            .collect(Collectors.toMap(TicketType::getId, Function.identity()));

            for(UpdateTicketTypeRequest ticketType:event.getTicketTypes())
                {
                    if(null==ticketType.getId())
                    {
                            //create
                            TicketType ticketTypeToCreate = new TicketType();
                            ticketTypeToCreate.setName(ticketType.getName());
                            ticketTypeToCreate.setPrice(ticketType.getPrice());
                            ticketTypeToCreate.setDescription(ticketType.getDescription());
                            ticketTypeToCreate.setTotalAvailable(ticketType.getTotalAvailable());
                            ticketTypeToCreate.setEvent(existingEvent);
                            existingEvent.getTicketTypes().add(ticketTypeToCreate);
                    }
                    else if(existingTicketTypesIndex.containsKey(ticketType.getId()))
                    {
                       //update
                       TicketType existingTicketType=existingTicketTypesIndex.get(ticketType.getId());
                       existingTicketType.setName(ticketType.getName());
                       existingTicketType.setPrice(ticketType.getPrice());
                       existingTicketType.setDescription(ticketType.getDescription());
                       existingTicketType.setTotalAvailable(ticketType.getTotalAvailable());


                    }
                    else{
                        throw new TicketTypeNotFoundException(
                            String.format("Ticket type with ID '%s' does not exist ", ticketType.getId())
                        );
                    }
                }

        return eventRepository.save(existingEvent);

    }

    @Override
    @Transactional
    public void deleteEventForOrganizer(UUID organizerId, UUID id) {
        getEventForOrganizer(organizerId, id).ifPresent(eventRepository::delete);
    }

    @Override
    public Page<Event> listPublishedEvents(Pageable pageable) {
        return eventRepository.findByStatus(EventStatusEnum.PUBLISHED,pageable);
    }
    

   /*  @Override
    public com.tushar.ticket.services.Event createEvent(com.tushar.ticket.services.UUID organizerId,
            com.tushar.ticket.services.CreateEventRequest event) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createEvent'");
    }*/
}