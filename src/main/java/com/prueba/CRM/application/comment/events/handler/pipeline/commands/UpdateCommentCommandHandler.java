package com.prueba.CRM.application.comment.events.handler.pipeline.commands;

import an.awesome.pipelinr.Command;
import br.com.fluentvalidator.context.ValidationResult;
import com.prueba.CRM.application.comment.dto.pipeline.commands.UpdateCommentCommand;
import com.prueba.CRM.application.comment.services.commands.UpdateCommentCommandService;
import com.prueba.CRM.application.comment.validators.commands.UpdateCommentCommandValidator;
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
public class UpdateCommentCommandHandler implements Command.Handler<UpdateCommentCommand, StatusResponse> {

    @Autowired
    private UpdateCommentCommandValidator validator;
    @Autowired
    private UpdateCommentCommandService service;

    @Override
    public StatusResponse handle(UpdateCommentCommand command) {
        StatusResponse statusResponse;
        ValidationResult validationResult = this.validator.validate(command);
        if (!validationResult.isValid()) {
            statusResponse = new StatusResponse(Boolean.FALSE, ResponseMessage.INPUT_FIELD_ERROR_MESSAGE, null, HttpStatus.BAD_REQUEST);
            ValidadorUtil.agregarMensajesError(validationResult, statusResponse);
            return statusResponse;
        }
        try {
            statusResponse = this.service.updateComment(command);
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
