package com.prueba.CRM.application.task.validators.commands;

import br.com.fluentvalidator.AbstractValidator;
import br.com.fluentvalidator.predicate.ComparablePredicate;
import br.com.fluentvalidator.predicate.ObjectPredicate;
import br.com.fluentvalidator.predicate.StringPredicate;
import com.prueba.CRM.application.task.dto.pipeline.commands.AddTaskCommand;
import com.prueba.CRM.application.utils.ResponseMessage;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class AddTaskCommandValidator extends AbstractValidator<AddTaskCommand> {

    /**
     * rules for AddTaskCommand
     */
    @Override
    public void rules() {
        ruleFor(AddTaskCommand::getContactId)
                .must(Predicate.not(ObjectPredicate.nullValue()))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_REQUIRED, "contactId"))
                .withFieldName("contactId")
                .must(ComparablePredicate.greaterThanOrEqual(0L))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_MENOR_ZERO, "contactId"))
                .withFieldName("contactId");
        ruleFor(AddTaskCommand::getTitle)
                .must(Predicate.not(StringPredicate.stringEmptyOrNull()))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_REQUIRED, "title"))
                .withFieldName("title");
        ruleFor(AddTaskCommand::getResponsiblePerson)
                .must(Predicate.not(ObjectPredicate.nullValue()))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_REQUIRED, "responsiblePerson"))
                .withFieldName("responsiblePerson")
                .must(ComparablePredicate.greaterThanOrEqual(0L))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_MENOR_ZERO, "responsiblePerson"))
                .withFieldName("responsiblePerson");
        ruleFor(AddTaskCommand::getDeadlineDate)
                .must(Predicate.not(StringPredicate.stringEmptyOrNull()))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_REQUIRED, "deadlineDate"))
                .withFieldName("deadlineDate");
    }
}
