package com.prueba.CRM.infrastructure.repository;

import com.prueba.CRM.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Query(value = "SELECT contact FROM Contact contact WHERE name = :dataFound OR phone = :dataFound")
    Optional<List<Contact>> findByNameOrCell(@Param(value = "dataFound") String dataFound);
}
