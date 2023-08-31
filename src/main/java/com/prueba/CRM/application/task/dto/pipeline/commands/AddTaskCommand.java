package com.prueba.CRM.application.task.dto.pipeline.commands;

import an.awesome.pipelinr.Command;
import com.prueba.CRM.config.models.StatusResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddTaskCommand implements Command<StatusResponse> {
    private String title;
    private Long responsiblePerson;
    private String deadlineDate;
    private boolean taskStatusSummary;
    private Long contactId;
}
