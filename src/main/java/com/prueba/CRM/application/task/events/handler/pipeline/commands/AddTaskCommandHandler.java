package com.prueba.CRM.application.task.events.handler.pipeline.commands;

import an.awesome.pipelinr.Command;
import br.com.fluentvalidator.context.ValidationResult;
import com.prueba.CRM.application.task.dto.pipeline.commands.AddTaskCommand;
import com.prueba.CRM.application.task.services.commands.AddTaskCommandService;
import com.prueba.CRM.application.task.validators.commands.AddTaskCommandValidator;
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
public class AddTaskCommandHandler implements Command.Handler<AddTaskCommand, StatusResponse> {

    @Autowired
    private AddTaskCommandValidator validator;
    @Autowired
    private AddTaskCommandService service;

    @Override
    public StatusResponse handle(AddTaskCommand command) {
        StatusResponse statusResponse;
        ValidationResult validationResult = this.validator.validate(command);
        if (!validationResult.isValid()) {
            statusResponse = new StatusResponse(Boolean.FALSE, ResponseMessage.INPUT_FIELD_ERROR_MESSAGE, null, HttpStatus.BAD_REQUEST);
            ValidadorUtil.agregarMensajesError(validationResult, statusResponse);
            return statusResponse;
        }
        try {
            statusResponse = this.service.addTask(command);
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
