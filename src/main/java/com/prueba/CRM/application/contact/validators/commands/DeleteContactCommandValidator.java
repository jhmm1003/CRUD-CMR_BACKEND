package com.prueba.CRM.application.contact.validators.commands;

import br.com.fluentvalidator.AbstractValidator;
import br.com.fluentvalidator.predicate.ComparablePredicate;
import br.com.fluentvalidator.predicate.ObjectPredicate;
import com.prueba.CRM.application.contact.dto.pipeline.commands.DeleteContactCommand;
import com.prueba.CRM.application.utils.ResponseMessage;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class DeleteContactCommandValidator extends AbstractValidator<DeleteContactCommand> {

    /**
     * rules for DeleteContactCommand
     */
    @Override
    public void rules() {
        ruleFor(DeleteContactCommand::getId)
                .must(Predicate.not(ObjectPredicate.nullValue()))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_REQUIRED, "id"))
                .withFieldName("id")
                .must(ComparablePredicate.greaterThanOrEqual(0L))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_MENOR_ZERO, "id"))
                .withFieldName("id");
    }
}
