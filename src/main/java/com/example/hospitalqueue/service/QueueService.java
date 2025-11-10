package com.example.hospitalqueue.service;

import com.example.hospitalqueue.model.QueueEntry;
import com.example.hospitalqueue.model.QueueStatus;
import com.example.hospitalqueue.repository.QueueRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class QueueService {

    private final QueueRepository repo;

    public QueueService(QueueRepository repo) {
        this.repo = repo;
    }

    public QueueEntry register(String patientName, String department) {
        QueueEntry e = QueueEntry.builder()
                .patientName(patientName)
                .department(department)
                .status(QueueStatus.WAITING)
                .registeredAt(OffsetDateTime.now())
                .build();
        return repo.save(e);
    }

    public List<QueueEntry> listAll() {
        return repo.findAllByOrderByRegisteredAtAsc();
    }

    public Optional<QueueEntry> findById(Long id) {
        return repo.findById(id);
    }

    public QueueEntry updateStatus(Long id, QueueStatus status) {
        QueueEntry e = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found"));
        e.setStatus(status);
        return repo.save(e);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
