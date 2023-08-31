package com.prueba.CRM.application.contact.events.handler.pipeline.querys;

import an.awesome.pipelinr.Command;
import com.prueba.CRM.application.contact.dto.pipeline.querys.SearchAllContactsQuery;
import com.prueba.CRM.application.contact.services.querys.SearchAllContactsQueryService;
import com.prueba.CRM.config.exceptions.CustomDataNotFoundException;
import com.prueba.CRM.config.models.StatusResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SearchAllContactsQueryHandler implements Command.Handler<SearchAllContactsQuery, StatusResponse> {

    @Autowired
    private SearchAllContactsQueryService service;

    @Override
    public StatusResponse handle(SearchAllContactsQuery query) {
        StatusResponse statusResponse;
        try {
            statusResponse = this.service.searchAllContacts();
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
