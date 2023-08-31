package com.prueba.CRM.application.contact.services.commands;

import com.prueba.CRM.application.contact.dto.pipeline.commands.DeleteContactCommand;
import com.prueba.CRM.application.utils.ResponseMessage;
import com.prueba.CRM.config.exceptions.CustomDataNotFoundException;
import com.prueba.CRM.config.models.StatusResponse;
import com.prueba.CRM.infrastructure.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DeleteContactCommadService {
    @Autowired
    private ContactRepository contactRepository;

    public StatusResponse deleteContact(DeleteContactCommand command) {
        Boolean existsUser = contactRepository.existsById(command.getId());
        if (Boolean.FALSE.equals(existsUser)) {
            throw new CustomDataNotFoundException(
                    String.format(ResponseMessage.MESSAGE_CONTACT_ID_NOT_EXISTS, command.getId())
            );
        }
        contactRepository.deleteById(command.getId());
        return new StatusResponse(Boolean.TRUE, "Deleted contact", null, HttpStatus.OK);
    }
}
