package com.prueba.CRM.application.contact.dto.pipeline.querys;

import an.awesome.pipelinr.Command;
import com.prueba.CRM.config.models.StatusResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SearchAllContactsQuery implements Command<StatusResponse> {
}
