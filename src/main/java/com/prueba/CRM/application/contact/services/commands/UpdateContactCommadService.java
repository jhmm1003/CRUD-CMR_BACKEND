package com.prueba.CRM.application.contact.services.commands;

import com.prueba.CRM.application.contact.dto.pipeline.commands.UpdateContactCommand;
import com.prueba.CRM.application.utils.ResponseMessage;
import com.prueba.CRM.config.exceptions.CustomDataNotFoundException;
import com.prueba.CRM.config.models.StatusResponse;
import com.prueba.CRM.domain.Contact;
import com.prueba.CRM.infrastructure.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class UpdateContactCommadService {
    @Autowired
    private ContactRepository contactRepository;

    public StatusResponse updateContact(UpdateContactCommand command) {
        Optional<Contact> contactOptional = contactRepository.findById(command.getId());
        if (!contactOptional.isPresent()) {
            throw new CustomDataNotFoundException(
                    String.format(ResponseMessage.MESSAGE_CONTACT_ID_NOT_EXISTS, command.getId())
            );
        }
        Contact contact = contactOptional.get();
        contact.updateContact(command.getName(), command.getLastName(), command.getEmail(),
                command.getPhone(), command.getBirthDate(),
                command.getAddress(), command.getTypeContact(), command.getOrigin());
        contactRepository.save(contact);
        return new StatusResponse(Boolean.TRUE, "Updated contact", null, HttpStatus.OK);
    }
}
