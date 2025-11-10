package com.example.hospitalqueue.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "queue_entry")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QueueEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patientName;

    private String department;

    @Enumerated(EnumType.STRING)
    private QueueStatus status;

    private OffsetDateTime registeredAt;
}
