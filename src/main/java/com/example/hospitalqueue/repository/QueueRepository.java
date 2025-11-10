package com.example.hospitalqueue.repository;

import com.example.hospitalqueue.model.QueueEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueueRepository extends JpaRepository<QueueEntry, Long> {
    List<QueueEntry> findAllByOrderByRegisteredAtAsc();
}
