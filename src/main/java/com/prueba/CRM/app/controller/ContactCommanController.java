package com.prueba.CRM.app.controller;

import an.awesome.pipelinr.Pipeline;
import com.prueba.CRM.application.contact.dto.pipeline.commands.AddContactCommand;
import com.prueba.CRM.application.contact.dto.pipeline.commands.DeleteContactCommand;
import com.prueba.CRM.application.contact.dto.pipeline.commands.UpdateContactCommand;
import com.prueba.CRM.application.contact.dto.pipeline.querys.SearchAllContactsQuery;
import com.prueba.CRM.application.contact.dto.pipeline.querys.SearchContactByIdQuery;
import com.prueba.CRM.application.contact.dto.pipeline.querys.SearchContactByNameOrCellQuery;
import com.prueba.CRM.config.models.StatusResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/contact")
public class ContactCommanController {
    @Autowired
    private Pipeline pipeline;

    @PostMapping
    public ResponseEntity<StatusResponse> addContact(@RequestBody AddContactCommand command) {
        StatusResponse response = this.pipeline.send(command);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @PutMapping
    public ResponseEntity<StatusResponse> updateContact(@RequestBody UpdateContactCommand command) {
        StatusResponse response = this.pipeline.send(command);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @DeleteMapping
    public ResponseEntity<StatusResponse> deleteContact(@RequestParam Long contactId) {
        DeleteContactCommand command = new DeleteContactCommand(contactId);
        StatusResponse response = this.pipeline.send(command);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping
    public ResponseEntity<StatusResponse> searchContactById(@RequestParam Long contactId) {
        SearchContactByIdQuery query = new SearchContactByIdQuery(contactId);
        StatusResponse response = this.pipeline.send(query);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }
/*
    @GetMapping("/contactbyname/")
    public ResponseEntity<StatusResponse> searchContactByNameOrCell(@RequestParam String dataFound) {
        SearchContactByNameOrCellQuery query = new SearchContactByNameOrCellQuery(dataFound);
        StatusResponse response = this.pipeline.send(query);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }*/

    @GetMapping("/contacts")
    public ResponseEntity<StatusResponse> SearchAllContacts() {
        SearchAllContactsQuery command = new SearchAllContactsQuery();
        StatusResponse response = this.pipeline.send(command);
        log.info("Searching contacts: " + response.getData());
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }
}
