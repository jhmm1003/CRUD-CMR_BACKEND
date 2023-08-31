package com.prueba.CRM.application.contact.services.querys;

import com.prueba.CRM.application.contact.dto.response.ContactResponse;
import com.prueba.CRM.application.utils.Constants;
import com.prueba.CRM.application.utils.Tools;
import com.prueba.CRM.config.models.StatusResponse;
import com.prueba.CRM.domain.Contact;
import com.prueba.CRM.infrastructure.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class SearchAllContactsQueryService {
    @Autowired
    private ContactRepository contactRepository;

    public StatusResponse searchAllContacts() {
        List<Contact> contactList = contactRepository.findAll();
        List<ContactResponse> result = new ArrayList<>();
        contactList.stream().forEach(contactOptional -> result.add(contactToContactResponse(contactOptional)));
        return new StatusResponse(Boolean.TRUE, "Contact found", result, HttpStatus.OK);
    }
    public ContactResponse contactToContactResponse(Contact contact) {
        return new ContactResponse(contact.getId(), contact.getName(), contact.getLastName(),
                contact.getEmail(), contact.getPhone(), Tools.localDateToString(contact.getBirthDate(),
                Constants.LOCAL_DATE_FORMAT), contact.getAddress(), contact.getTypeContact(), contact.getOrigin());
    }
}


