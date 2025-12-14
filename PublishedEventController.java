package com.tushar.ticket.controllers;

import jakarta.annotation.PostConstruct;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tushar.ticket.domain.dtos.ListEventPublishedResponseDto;
import com.tushar.ticket.domain.dtos.PageResponse;
import com.tushar.ticket.domain.entities.Event;
import com.tushar.ticket.mappers.EventMapper;
import com.tushar.ticket.services.EventService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/*public class PublishedEventController {
   private static final Logger log = LoggerFactory.getLogger(PublishedEventController.class);
    private final EventService eventService;
    private final EventMapper eventMapper;
    @PostConstruct
public void init() {
    System.err.println("🔥 PublishedEventController LOADED");
}

    @GetMapping
 public ResponseEntity<PageResponse<ListEventPublishedResponseDto>>listPublishedEvents(Pageable pageable){
    System.err.println("🔥 CONTROLLER METHOD HIT");
    log.info("Pageable = {}", pageable);
    
/*Page<ListEventPublishedResponseDto>dtoPage=eventService.listPublishedEvents(pageable).map(eventMapper::toListEventPublishedResponseDto);

    return ResponseEntity.ok(new PageResponse<>(dtoPage.getContent(),dtoPage.getNumber(), dtoPage.getSize(),dtoPage.getTotalElements(), dtoPage.getTotalPages()));
    Page<ListEventPublishedResponseDto> dtoPage =
            eventService
                    .listPublishedEvents(pageable)
                    .map(eventMapper::toListEventPublishedResponseDto);
                    log.info("Returning {} published events", dtoPage.getNumberOfElements());

    PageResponse<ListEventPublishedResponseDto> response =
            new PageResponse<>(
                    dtoPage.getContent(),
                    dtoPage.getNumber(),
                    dtoPage.getSize(),
                    dtoPage.getTotalElements(),  
                    dtoPage.getTotalPages()     
                       
            );

    return ResponseEntity.ok(response);

 }   
 @RestController
@RequestMapping(path="/api/v1/published-events")
@RequiredArgsConstructor
public class PublishedEventController {
    
    private static final Logger log = LoggerFactory.getLogger(PublishedEventController.class);
    
    private final EventService eventService;
    private final EventMapper eventMapper;
    
    @PostConstruct
    public void init() {
        System.err.println("🔥 PublishedEventController LOADED");
    }

    // List all published events
    @GetMapping
    public ResponseEntity<PageResponse<ListEventPublishedResponseDto>> listPublishedEvents(Pageable pageable) {
        System.err.println("🔥 LIST ENDPOINT HIT");
        log.info("Pageable = {}", pageable);
        
        try {
            Page<ListEventPublishedResponseDto> dtoPage = eventService
                    .listPublishedEvents(pageable)
                    .map(eventMapper::toListEventPublishedResponseDto);
            
            log.info("Returning {} published events", dtoPage.getNumberOfElements());

            PageResponse<ListEventPublishedResponseDto> response = new PageResponse<>(
                    dtoPage.getContent(),
                    dtoPage.getNumber(),
                    dtoPage.getSize(),
                    dtoPage.getTotalElements(),
                    dtoPage.getTotalPages()
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("❌ Error listing events", e);
            throw e;
        }
    }
    
    // ⚠️ ADD THIS METHOD - Get single published event by ID
    @GetMapping("/{id}")
    public ResponseEntity<PublishedEventDetails> getPublishedEvent(@PathVariable String id) {
        System.err.println("🔥 GET SINGLE EVENT ENDPOINT HIT - ID: " + id);
        log.info("Fetching published event with id: {}", id);
        
        try {
            // You need to implement this method in EventService
            PublishedEventDetails event = eventService.getPublishedEventById(id);
            
            if (event == null) {
                log.warn("❌ Event not found: {}", id);
                return ResponseEntity.notFound().build();
            }
            
            log.info("✅ Returning event: {}", event.getName());
            return ResponseEntity.ok(event);
            
        } catch (Exception e) {
            log.error("❌ Error fetching event: {}", id, e);
            e.printStackTrace();
            throw e;
        }
    }
}*/

@RestController
@RequestMapping(path="/api/v1/published-events")
@RequiredArgsConstructor
public class PublishedEventController {
    private static final Logger log = LoggerFactory.getLogger(PublishedEventController.class);
    
    private final EventService eventService;
    private final EventMapper eventMapper;
    
    @PostConstruct
    public void init() {
        System.err.println("🔥 PublishedEventController LOADED");
    }

    // List all published events
    @GetMapping
    public ResponseEntity<PageResponse<ListEventPublishedResponseDto>> listPublishedEvents(Pageable pageable) {
        System.err.println("🔥 LIST ENDPOINT HIT");
        log.info("Pageable = {}", pageable);
        
        try {
            Page<ListEventPublishedResponseDto> dtoPage = eventService
                    .listPublishedEvents(pageable)
                    .map(eventMapper::toListEventPublishedResponseDto);
            
            log.info("Returning {} published events", dtoPage.getNumberOfElements());

            PageResponse<ListEventPublishedResponseDto> response = new PageResponse<>(
                    dtoPage.getContent(),
                    dtoPage.getNumber(),
                    dtoPage.getSize(),
                    dtoPage.getTotalElements(),
                    dtoPage.getTotalPages()
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("❌ Error listing events", e);
            throw e;
        }
    }
    
    // ⚠️ ADD THIS METHOD - Get single published event by ID
    @GetMapping("/{id}")
    public ResponseEntity<PublishedEventDetails> getPublishedEvent(@PathVariable String id) {
        System.err.println("🔥 GET SINGLE EVENT ENDPOINT HIT - ID: " + id);
        log.info("Fetching published event with id: {}", id);
        
        try {
            // You need to implement this method in EventService
            PublishedEventDetails event = eventService.getPublishedEventById(id);
            
            if (event == null) {
                log.warn("❌ Event not found: {}", id);
                return ResponseEntity.notFound().build();
            }
            
            log.info("✅ Returning event: {}", event.getName());
            return ResponseEntity.ok(event);
            
        } catch (Exception e) {
            log.error("❌ Error fetching event: {}", id, e);
            e.printStackTrace();
            throw e;
        }
    }
}