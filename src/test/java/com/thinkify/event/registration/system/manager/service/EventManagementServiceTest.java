package com.thinkify.event.registration.system.manager.service;

import com.thinkify.event.registration.system.exception.BadRequest;
import com.thinkify.event.registration.system.manager.managerDTO.EventsEntity;
import com.thinkify.event.registration.system.manager.managerDTO.RegistrationDetails;
import com.thinkify.event.registration.system.manager.repository.EventManagementRepository;
import com.thinkify.event.registration.system.manager.repository.RegistrationDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventManagementServiceTest {

    @Mock
    private EventManagementRepository eventManagementRepository;

    @Mock
    private RegistrationDetailsRepository registrationDetailsRepository;

    @InjectMocks
    private EventManagementService eventManagementService;

    private EventsEntity eventsEntity;
    private RegistrationDetails registrationDetails;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        registrationDetails = new RegistrationDetails();
        eventsEntity = EventsEntity.builder()
                .id(1L)
                .eventName("Test Event")
                .eventDate(new Date())
                .eventLocation("Test Location")
                .registrationDetails(registrationDetails)
                .build();
    }

    @Test
    void getEvents_shouldReturnEvents_whenEventsExist() {
        when(eventManagementRepository.findAll()).thenReturn(Collections.singletonList(eventsEntity));
        assertFalse(eventManagementService.getEvents().isEmpty());
    }

    @Test
    void getEvents_shouldThrowException_whenNoEventsExist() {
        when(eventManagementRepository.findAll()).thenReturn(Collections.emptyList());
        Exception exception = assertThrows(BadRequest.class, () -> eventManagementService.getEvents());
        assertEquals("No events available", exception.getMessage());
    }

    @Test
    void getEvent_shouldReturnEvent_whenEventExists() {
        when(eventManagementRepository.existsById(1L)).thenReturn(true);
        when(eventManagementRepository.findById(1L)).thenReturn(Optional.of(eventsEntity));
        assertTrue(eventManagementService.getEvent(1L).isPresent());
    }

    @Test
    void getEvent_shouldThrowException_whenEventDoesNotExist() {
        when(eventManagementRepository.existsById(1L)).thenReturn(false);
        Exception exception = assertThrows(BadRequest.class, () -> eventManagementService.getEvent(1L));
        assertEquals("Event with id 1 does not exist", exception.getMessage());
    }

    @Test
    void createEvent_shouldSaveAndReturnEvent() {
        when(registrationDetailsRepository.save(registrationDetails)).thenReturn(registrationDetails);
        when(eventManagementRepository.save(eventsEntity)).thenReturn(eventsEntity);
        assertEquals(eventsEntity, eventManagementService.createEvent(eventsEntity));
    }

    @Test
    void updateEvent_shouldUpdateAndReturnEvent_whenEventIsDifferent() {
        EventsEntity updatedEntity = EventsEntity.builder()
                .id(1L)
                .eventName("Updated Event")
                .eventDate(new Date())
                .eventLocation("Updated Location")
                .registrationDetails(registrationDetails)
                .build();

        when(eventManagementRepository.findById(1L)).thenReturn(Optional.of(eventsEntity));
        when(eventManagementRepository.save(any(EventsEntity.class))).thenReturn(updatedEntity);

        EventsEntity result = eventManagementService.updateEvent(1L, updatedEntity);
        assertEquals("Updated Event", result.getEventName());
    }

    @Test
    void updateEvent_shouldThrowException_whenNoChangeInRequest() {
        when(eventManagementRepository.findById(1L)).thenReturn(Optional.of(eventsEntity));
        Exception exception = assertThrows(BadRequest.class, () -> eventManagementService.updateEvent(1L, eventsEntity));
        assertEquals("No change in the passed request", exception.getMessage());
    }

    @Test
    void deleteEvent_shouldDeleteAndReturnEvent_whenEventExists() {
        when(eventManagementRepository.existsById(1L)).thenReturn(true);
        when(eventManagementRepository.findById(1L)).thenReturn(Optional.of(eventsEntity));
        doNothing().when(eventManagementRepository).deleteById(1L);

        Optional<EventsEntity> result = eventManagementService.deleteEvent(1L);
        assertTrue(result.isPresent());
    }

    @Test
    void deleteEvent_shouldThrowException_whenEventDoesNotExist() {
        when(eventManagementRepository.existsById(1L)).thenReturn(false);
        Exception exception = assertThrows(BadRequest.class, () -> eventManagementService.deleteEvent(1L));
        assertEquals("Event with id 1 does not exist", exception.getMessage());
    }
}