package com.thinkify.event.registration.system.manager.repository;

import com.thinkify.event.registration.system.manager.managerDTO.EventsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventManagementRepository extends JpaRepository<EventsEntity, Long> {
}
