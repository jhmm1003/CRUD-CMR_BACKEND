package com.prueba.CRM.infrastructure.repository;

import com.prueba.CRM.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT comment FROM Comment comment WHERE contact.id = :contactId")
    Optional<List<Comment>> searchCommentsByContact(@Param(value = "contactId") Long contactId);
}
