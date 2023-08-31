package com.prueba.CRM.application.task.dto.response;

import com.prueba.CRM.application.contact.dto.response.ContactResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponse implements Serializable {
    private Long id;
    private String title;
    private ContactResponse responsiblePerson;
    private String deadlineDate;
    private boolean taskStatusSummary;
    private String taskCreationDate;
}
