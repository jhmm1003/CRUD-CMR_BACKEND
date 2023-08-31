package com.prueba.CRM.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String commentText;
    private LocalDateTime commentCreationDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id")
    private Contact contact;

    public Comment(String commentText, Contact contact) {
        this.commentText = commentText;
        this.commentCreationDate = LocalDateTime.now();
        this.contact = contact;
    }

    public void updateComment(String commentText) {
        this.commentText = commentText;
        this.commentCreationDate = LocalDateTime.now();
    }
}
