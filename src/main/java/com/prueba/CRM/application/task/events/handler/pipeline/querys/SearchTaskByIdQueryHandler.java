package com.prueba.CRM.application.task.events.handler.pipeline.querys;

import an.awesome.pipelinr.Command;
import br.com.fluentvalidator.context.ValidationResult;
import com.prueba.CRM.application.task.dto.pipeline.querys.SearchTaskByIdQuery;
import com.prueba.CRM.application.task.services.querys.SearchTaskByIdQueryService;
import com.prueba.CRM.application.task.validators.querys.SearchTaskByIdQueryValidator;
import com.prueba.CRM.application.utils.ResponseMessage;
import com.prueba.CRM.application.utils.ValidadorUtil;
import com.prueba.CRM.config.exceptions.CustomDataNotFoundException;
import com.prueba.CRM.config.models.StatusResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SearchTaskByIdQueryHandler implements Command.Handler<SearchTaskByIdQuery, StatusResponse> {

    @Autowired
    private SearchTaskByIdQueryValidator validator;
    @Autowired
    private SearchTaskByIdQueryService service;

    @Override
    public StatusResponse handle(SearchTaskByIdQuery query) {
        StatusResponse statusResponse;
        ValidationResult validationResult = this.validator.validate(query);
        if (!validationResult.isValid()) {
            statusResponse = new StatusResponse(Boolean.FALSE, ResponseMessage.INPUT_FIELD_ERROR_MESSAGE, null, HttpStatus.BAD_REQUEST);
            ValidadorUtil.agregarMensajesError(validationResult, statusResponse);
            return statusResponse;
        }
        try {
            statusResponse = this.service.searchTaskById(query);
        } catch (CustomDataNotFoundException e) {
            log.error("Mensaje error...");
            log.error(e.getMessage());
            statusResponse = new StatusResponse(Boolean.FALSE, "Mensaje error...", null, HttpStatus.NOT_FOUND);
            statusResponse.AddMessage(e.getMessage());
        } catch (Exception e) {
            log.error("Mensaje error...");
            log.error(e.getMessage(), e);
            statusResponse = new StatusResponse(Boolean.FALSE, "Mensaje error...", null, HttpStatus.INTERNAL_SERVER_ERROR);
            statusResponse.AddMessage(e.getMessage());
        }
        return statusResponse;
    }
}
