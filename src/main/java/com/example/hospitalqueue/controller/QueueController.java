package com.example.hospitalqueue.controller;

import com.example.hospitalqueue.model.QueueEntry;
import com.example.hospitalqueue.model.QueueStatus;
import com.example.hospitalqueue.service.QueueService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/queue")
@Validated
public class QueueController {

    private final QueueService service;

    public QueueController(QueueService service) {
        this.service = service;
    }

    record CreateRequest(@NotBlank String patientName, @NotBlank String department) {}

    record UpdateStatusRequest(@NotBlank String status) {}

    @GetMapping
    public List<QueueEntry> list() {
        return service.listAll();
    }

    @PostMapping
    public ResponseEntity<QueueEntry> create(@Valid @RequestBody CreateRequest req) {
        QueueEntry created = service.register(req.patientName(), req.department());
        return ResponseEntity.created(URI.create("/api/queue/" + created.getId())).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QueueEntry> get(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<QueueEntry> updateStatus(@PathVariable Long id, @Valid @RequestBody UpdateStatusRequest req) {
        QueueStatus st;
        try {
            st = QueueStatus.valueOf(req.status().toUpperCase());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
        QueueEntry updated = service.updateStatus(id, st);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
