package com.prueba.CRM.application.contact.services.commands;

import com.prueba.CRM.application.contact.dto.pipeline.commands.AddContactCommand;
import com.prueba.CRM.config.models.StatusResponse;
import com.prueba.CRM.domain.Contact;
import com.prueba.CRM.infrastructure.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AddContactCommadService {
    @Autowired
    private ContactRepository contactRepository;

    public StatusResponse addContact(AddContactCommand command) {
        Contact contact = new Contact(command.getName(),command.getLastName(),command.getEmail(),
                command.getPhone(), command.getBirthDate(),
                command.getAddress(),command.getTypeContact(), command.getOrigin());
        contactRepository.save(contact);
        return new StatusResponse(Boolean.TRUE,"Saved contact",null, HttpStatus.OK);
    }
}
