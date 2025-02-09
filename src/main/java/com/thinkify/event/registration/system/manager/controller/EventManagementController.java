package com.thinkify.event.registration.system.manager.controller;

import com.thinkify.event.registration.system.manager.managerDTO.EventsEntity;
import com.thinkify.event.registration.system.manager.service.EventManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/event-manager")
public class EventManagementController {

    @Autowired
    private EventManagementService eventManagementService;

    @GetMapping("/events")
    public ResponseEntity<List<EventsEntity>> getEvents() {;
        List<EventsEntity> events = eventManagementService.getEvents();
        if (events.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(events);
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<Optional<EventsEntity>> getEvent(@PathVariable Long id) {
        return ResponseEntity.ok(eventManagementService.getEvent(id));
    }

    @PostMapping("/event")
    public ResponseEntity<EventsEntity> createEvent(@RequestBody EventsEntity eventsEntity) {
        return ResponseEntity.ok(eventManagementService.createEvent(eventsEntity));
    }

    @PutMapping("/event/{id}")
    public ResponseEntity<EventsEntity> updateEvent(@RequestBody EventsEntity eventsEntity, @PathVariable Long id) {
        return ResponseEntity.ok(eventManagementService.updateEvent(id, eventsEntity));
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity<Optional<EventsEntity>> deleteEvent(@PathVariable Long id) {
        return ResponseEntity.ok(eventManagementService.deleteEvent(id));
    }
}
