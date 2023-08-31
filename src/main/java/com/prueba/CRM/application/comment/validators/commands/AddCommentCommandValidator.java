package com.prueba.CRM.application.comment.validators.commands;

import br.com.fluentvalidator.AbstractValidator;
import br.com.fluentvalidator.predicate.ComparablePredicate;
import br.com.fluentvalidator.predicate.ObjectPredicate;
import br.com.fluentvalidator.predicate.StringPredicate;
import com.prueba.CRM.application.comment.dto.pipeline.commands.AddCommentCommand;
import com.prueba.CRM.application.utils.ResponseMessage;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class AddCommentCommandValidator extends AbstractValidator<AddCommentCommand> {

    /**
     * rules for AddCommentCommand
     */
    @Override
    public void rules() {
        ruleFor(AddCommentCommand::getContactId)
                .must(Predicate.not(ObjectPredicate.nullValue()))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_REQUIRED, "contactId"))
                .withFieldName("contactId")
                .must(ComparablePredicate.greaterThanOrEqual(0L))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_MENOR_ZERO, "contactId"))
                .withFieldName("contactId");
        ruleFor(AddCommentCommand::getCommentText)
                .must(Predicate.not(StringPredicate.stringEmptyOrNull()))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_REQUIRED, "commentText"))
                .withFieldName("commentText");
    }
}
