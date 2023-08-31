package com.prueba.CRM.application.task.validators.commands;

import br.com.fluentvalidator.AbstractValidator;
import br.com.fluentvalidator.predicate.ComparablePredicate;
import br.com.fluentvalidator.predicate.ObjectPredicate;
import br.com.fluentvalidator.predicate.StringPredicate;
import com.prueba.CRM.application.task.dto.pipeline.commands.UpdateTaskCommand;
import com.prueba.CRM.application.utils.ResponseMessage;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class UpdateTaskCommandValidator extends AbstractValidator<UpdateTaskCommand> {

    /**
     * rules for UpdateTaskCommand
     */
    @Override
    public void rules() {
        ruleFor(UpdateTaskCommand::getId)
                .must(Predicate.not(ObjectPredicate.nullValue()))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_REQUIRED, "id"))
                .withFieldName("id")
                .must(ComparablePredicate.greaterThanOrEqual(0L))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_MENOR_ZERO, "id"))
                .withFieldName("id");
        ruleFor(UpdateTaskCommand::getContactId)
                .must(Predicate.not(ObjectPredicate.nullValue()))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_REQUIRED, "contactId"))
                .withFieldName("contactId")
                .must(ComparablePredicate.greaterThanOrEqual(0L))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_MENOR_ZERO, "contactId"))
                .withFieldName("contactId");
        ruleFor(UpdateTaskCommand::getTitle)
                .must(Predicate.not(StringPredicate.stringEmptyOrNull()))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_REQUIRED, "title"))
                .withFieldName("title");
        ruleFor(UpdateTaskCommand::getResponsiblePerson)
                .must(Predicate.not(ObjectPredicate.nullValue()))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_REQUIRED, "responsiblePerson"))
                .withFieldName("responsiblePerson")
                .must(ComparablePredicate.greaterThanOrEqual(0L))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_MENOR_ZERO, "responsiblePerson"))
                .withFieldName("responsiblePerson");
        ruleFor(UpdateTaskCommand::getDeadlineDate)
                .must(Predicate.not(StringPredicate.stringEmptyOrNull()))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_REQUIRED, "deadlineDate"))
                .withFieldName("deadlineDate");
    }
}
