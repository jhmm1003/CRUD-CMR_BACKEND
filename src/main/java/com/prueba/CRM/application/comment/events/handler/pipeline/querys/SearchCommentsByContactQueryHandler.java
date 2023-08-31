package com.prueba.CRM.application.comment.events.handler.pipeline.querys;

import an.awesome.pipelinr.Command;
import br.com.fluentvalidator.context.ValidationResult;
import com.prueba.CRM.application.comment.dto.pipeline.querys.SearchCommentsByContactQuery;
import com.prueba.CRM.application.comment.services.querys.SearchCommentsByContactQueryService;
import com.prueba.CRM.application.comment.validators.querys.SearchCommentsByContactQueryValidator;
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
public class SearchCommentsByContactQueryHandler implements Command.Handler<SearchCommentsByContactQuery, StatusResponse> {

    @Autowired
    private SearchCommentsByContactQueryValidator validator;
    @Autowired
    private SearchCommentsByContactQueryService service;

    @Override
    public StatusResponse handle(SearchCommentsByContactQuery query) {
        StatusResponse statusResponse;
        ValidationResult validationResult = this.validator.validate(query);
        if (!validationResult.isValid()) {
            statusResponse = new StatusResponse(Boolean.FALSE, ResponseMessage.INPUT_FIELD_ERROR_MESSAGE, null, HttpStatus.BAD_REQUEST);
            ValidadorUtil.agregarMensajesError(validationResult, statusResponse);
            return statusResponse;
        }
        try {
            statusResponse = this.service.searchCommentsByContact(query);
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
