package com.prueba.CRM.infrastructure.repository;

import com.prueba.CRM.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = "SELECT task FROM Task task WHERE contact.id = :contactId")
    Optional<List<Task>> searchTasksByContact(@Param(value = "contactId") Long contactId);
}
