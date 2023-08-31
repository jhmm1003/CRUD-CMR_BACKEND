package com.prueba.CRM.application.contact.services.querys;

import com.prueba.CRM.application.contact.dto.pipeline.querys.SearchContactByIdQuery;
import com.prueba.CRM.application.contact.dto.response.ContactResponse;
import com.prueba.CRM.application.utils.Constants;
import com.prueba.CRM.application.utils.ResponseMessage;
import com.prueba.CRM.application.utils.Tools;
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
public class SearchContactByIdQueryService {
    @Autowired
    private ContactRepository contactRepository;

    public StatusResponse serchContactById(SearchContactByIdQuery query) {
        Optional<Contact> contactOptional = contactRepository.findById(query.getId());
        if (!contactOptional.isPresent()) {
            throw new CustomDataNotFoundException(
                    String.format(ResponseMessage.MESSAGE_CONTACT_ID_NOT_EXISTS, query.getId())
            );
        }
        Contact contact = contactOptional.get();
        ContactResponse response = new ContactResponse(contact.getId(),
                contact.getName(), contact.getLastName(), contact.getEmail(),
                contact.getPhone(), Tools.localDateToString(contact.getBirthDate(), Constants.LOCAL_DATE_FORMAT),
                contact.getAddress(), contact.getTypeContact(), contact.getOrigin());
        return new StatusResponse(Boolean.TRUE,"Contact found",response, HttpStatus.OK);
    }
}
