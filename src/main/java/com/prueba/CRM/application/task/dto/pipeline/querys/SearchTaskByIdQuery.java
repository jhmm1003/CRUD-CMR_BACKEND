package com.prueba.CRM.application.task.dto.pipeline.querys;

import an.awesome.pipelinr.Command;
import com.prueba.CRM.config.models.StatusResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchTaskByIdQuery implements Command<StatusResponse> {
    private Long id;
}
