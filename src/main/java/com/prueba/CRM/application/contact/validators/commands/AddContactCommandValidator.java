package com.prueba.CRM.application.contact.validators.commands;

import br.com.fluentvalidator.AbstractValidator;
import br.com.fluentvalidator.predicate.StringPredicate;
import com.prueba.CRM.application.contact.dto.pipeline.commands.AddContactCommand;
import com.prueba.CRM.application.utils.ResponseMessage;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class AddContactCommandValidator extends AbstractValidator<AddContactCommand> {

    /**
     * rules for AddContactCommand
     */
    @Override
    public void rules() {
        ruleFor(AddContactCommand::getName)
                .must(Predicate.not(StringPredicate.stringEmptyOrNull()))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_REQUIRED, "name"))
                .withFieldName("name");
        ruleFor(AddContactCommand::getLastName)
                .must(Predicate.not(StringPredicate.stringEmptyOrNull()))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_REQUIRED, "last name"))
                .withFieldName("last name");
        ruleFor(AddContactCommand::getEmail)
                .must(Predicate.not(StringPredicate.stringEmptyOrNull()))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_REQUIRED, "email"))
                .withFieldName("email");
        ruleFor(AddContactCommand::getPhone)
                .must(Predicate.not(StringPredicate.stringEmptyOrNull()))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_REQUIRED, "phone"))
                .withFieldName("phone");
        ruleFor(AddContactCommand::getAddress)
                .must(Predicate.not(StringPredicate.stringEmptyOrNull()))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_REQUIRED, "address"))
                .withFieldName("address");
        ruleFor(AddContactCommand::getTypeContact)
                .must(Predicate.not(StringPredicate.stringEmptyOrNull()))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_REQUIRED, "type contact"))
                .withFieldName("type contact");
        ruleFor(AddContactCommand::getOrigin)
                .must(Predicate.not(StringPredicate.stringEmptyOrNull()))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_REQUIRED, "origin"))
                .withFieldName("origin");
    }
}
