package com.prueba.CRM.application.contact.dto.pipeline.commands;

import an.awesome.pipelinr.Command;
import com.prueba.CRM.config.models.StatusResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateContactCommand implements Command<StatusResponse> {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate birthDate;
    private String address;
    private String typeContact;
    private String origin;
}
