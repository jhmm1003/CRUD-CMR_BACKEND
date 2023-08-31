package com.prueba.CRM.application.task.dto.pipeline.commands;

import an.awesome.pipelinr.Command;
import com.prueba.CRM.config.models.StatusResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteTaskCommand implements Command<StatusResponse> {
    private Long id;
}
