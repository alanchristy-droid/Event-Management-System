package com.thinkify.event.registration.system.manager.service;

import com.thinkify.event.registration.system.exception.BadRequest;
import com.thinkify.event.registration.system.manager.managerDTO.EventsEntity;
import com.thinkify.event.registration.system.manager.managerDTO.RegistrationDetails;
import com.thinkify.event.registration.system.manager.repository.EventManagementRepository;
import com.thinkify.event.registration.system.manager.repository.RegistrationDetailsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventManagementService {

    @Autowired
    private EventManagementRepository eventManagementRepository;

    @Autowired
    private RegistrationDetailsRepository registrationDetailsRepository;

    public List<EventsEntity> getEvents() {
        List<EventsEntity> events = eventManagementRepository.findAll();
        if (events.isEmpty()) {
            throw new BadRequest("No events available");
        }
        return events;
    }

    public Optional<EventsEntity> getEvent(Long id) {
        boolean isEventPresent = eventManagementRepository.existsById(id);
        if (isEventPresent) {
            return eventManagementRepository.findById(id);
        } else {
            throw new BadRequest("Event with id " + id + " does not exist");
        }
    }

    public EventsEntity createEvent(EventsEntity eventsEntity) {
        registrationDetailsRepository.save(eventsEntity.getRegistrationDetails());
        return eventManagementRepository.save(eventsEntity);
    }

    @Transactional
    public EventsEntity updateEvent(Long id, EventsEntity eventsEntity) {
        EventsEntity existingEvent = eventManagementRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Event with id " + id + " does not exist"));

        boolean isDifferent = !existingEvent.getEventName().equals(eventsEntity.getEventName()) ||
                !existingEvent.getEventDate().equals(eventsEntity.getEventDate()) ||
                !existingEvent.getEventLocation().equals(eventsEntity.getEventLocation()) ||
                !existingEvent.getRegistrationDetails().equals(eventsEntity.getRegistrationDetails());

        if (!isDifferent) {
            throw new BadRequest("No change in the passed request");
        }

        existingEvent.setEventName(eventsEntity.getEventName());
        existingEvent.setEventDate(eventsEntity.getEventDate());
        existingEvent.setEventLocation(eventsEntity.getEventLocation());
        existingEvent.setRegistrationDetails(eventsEntity.getRegistrationDetails());
        registrationDetailsRepository.save(eventsEntity.getRegistrationDetails());
        return eventManagementRepository.save(existingEvent);
    }

    public Optional<EventsEntity> deleteEvent(Long id) {
        boolean isEventPresent = eventManagementRepository.existsById(id);
        if (isEventPresent) {
            Optional<EventsEntity> event = eventManagementRepository.findById(id);
            eventManagementRepository.deleteById(id);
            return event;
        } else {
            throw new BadRequest("Event with id " + id + " does not exist");
        }
    }
}
