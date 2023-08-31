package com.prueba.CRM.application.contact.validators.commands;

import br.com.fluentvalidator.AbstractValidator;
import br.com.fluentvalidator.predicate.ComparablePredicate;
import br.com.fluentvalidator.predicate.ObjectPredicate;
import br.com.fluentvalidator.predicate.StringPredicate;
import com.prueba.CRM.application.contact.dto.pipeline.commands.UpdateContactCommand;
import com.prueba.CRM.application.utils.ResponseMessage;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class UpdateContactCommandValidator extends AbstractValidator<UpdateContactCommand> {

    /**
     * rules for UpdateContactCommand
     */
    @Override
    public void rules() {
        ruleFor(UpdateContactCommand::getId)
                .must(Predicate.not(ObjectPredicate.nullValue()))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_REQUIRED, "id"))
                .withFieldName("id")
                .must(ComparablePredicate.greaterThanOrEqual(0L))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_MENOR_ZERO, "id"))
                .withFieldName("id");
        ruleFor(UpdateContactCommand::getName)
                .must(Predicate.not(StringPredicate.stringEmptyOrNull()))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_REQUIRED, "name"))
                .withFieldName("name");
        ruleFor(UpdateContactCommand::getLastName)
                .must(Predicate.not(StringPredicate.stringEmptyOrNull()))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_REQUIRED, "last name"))
                .withFieldName("last name");
        ruleFor(UpdateContactCommand::getEmail)
                .must(Predicate.not(StringPredicate.stringEmptyOrNull()))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_REQUIRED, "email"))
                .withFieldName("email");
        ruleFor(UpdateContactCommand::getPhone)
                .must(Predicate.not(StringPredicate.stringEmptyOrNull()))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_REQUIRED, "phone"))
                .withFieldName("phone");
        ruleFor(UpdateContactCommand::getAddress)
                .must(Predicate.not(StringPredicate.stringEmptyOrNull()))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_REQUIRED, "address"))
                .withFieldName("address");
        ruleFor(UpdateContactCommand::getTypeContact)
                .must(Predicate.not(StringPredicate.stringEmptyOrNull()))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_REQUIRED, "type contact"))
                .withFieldName("type contact");
        ruleFor(UpdateContactCommand::getOrigin)
                .must(Predicate.not(StringPredicate.stringEmptyOrNull()))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_REQUIRED, "origin"))
                .withFieldName("origin");
    }
}
