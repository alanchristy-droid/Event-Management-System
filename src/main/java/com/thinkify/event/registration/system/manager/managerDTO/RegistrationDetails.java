package com.thinkify.event.registration.system.manager.managerDTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "event_registration_details")
public class RegistrationDetails {

    @Id
    @GeneratedValue
    @Column(name = "registration_details_id")
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String event;
    private String status;
    @Column(name = "created")
    private Date created;

    @PrePersist
    void createdAt() {
        this.created = new Date();
    }

}
