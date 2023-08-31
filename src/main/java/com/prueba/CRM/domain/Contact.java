package com.prueba.CRM.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Contact implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "address")
    private String address;
    @Column(name = "type_contact")
    private String typeContact;
    @Column(name = "origin")
    private String origin;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contact", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    public Contact(String name, String lastName, String email, String phone, LocalDate birthDate, String address, String typeContact, String origin) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.address = address;
        this.typeContact = typeContact;
        this.origin = origin;
        this.comments = new ArrayList<>();
        this.tasks = new ArrayList<>();
    }

    public void updateContact(String name, String lastName, String email, String phone, LocalDate birthDate, String address, String typeContact, String origin) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.address = address;
        this.typeContact = typeContact;
        this.origin = origin;
    }
}