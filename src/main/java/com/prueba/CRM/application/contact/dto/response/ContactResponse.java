package com.prueba.CRM.application.contact.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactResponse implements Serializable {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String birthDate;
    private String address;
    private String typeContact;
    private String origin;
}
