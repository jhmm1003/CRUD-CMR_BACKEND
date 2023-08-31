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
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String title;
    private Contact responsiblePerson;
    private LocalDateTime deadlineDate;
    private boolean taskStatusSummary;
    private LocalDateTime taskCreationDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id")
    private Contact contact;

    public Task(String title, Contact responsiblePerson, LocalDateTime deadlineDate, boolean taskStatusSummary, Contact contact) {
        this.title = title;
        this.responsiblePerson = responsiblePerson;
        this.deadlineDate = deadlineDate;
        this.taskStatusSummary = taskStatusSummary;
        this.taskCreationDate = LocalDateTime.now();
        this.contact = contact;
    }

    public void updateTask(String title, Contact responsiblePerson, LocalDateTime deadlineDate, boolean taskStatusSummary) {
        this.title = title;
        this.responsiblePerson = responsiblePerson;
        this.deadlineDate = deadlineDate;
        this.taskStatusSummary = taskStatusSummary;
        this.taskCreationDate = LocalDateTime.now();
    }
}
