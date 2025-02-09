package com.thinkify.event.registration.system.manager.repository;

import com.thinkify.event.registration.system.manager.managerDTO.RegistrationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationDetailsRepository extends JpaRepository<RegistrationDetails, Long> {
}