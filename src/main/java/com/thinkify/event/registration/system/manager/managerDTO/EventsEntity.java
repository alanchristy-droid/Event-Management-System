package com.thinkify.event.registration.system.manager.managerDTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events")
public class EventsEntity {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String eventName;

    @NotNull
    private Date eventDate;

    @NotNull
    private String eventLocation;

    @NotNull
    @OneToOne
    @JoinColumn(name = "registration_details_id", referencedColumnName = "registration_details_id")
    private RegistrationDetails registrationDetails;
}
