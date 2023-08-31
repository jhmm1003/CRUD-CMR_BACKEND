package com.prueba.CRM.application.comment.validators.commands;

import br.com.fluentvalidator.AbstractValidator;
import br.com.fluentvalidator.predicate.ComparablePredicate;
import br.com.fluentvalidator.predicate.ObjectPredicate;
import br.com.fluentvalidator.predicate.StringPredicate;
import com.prueba.CRM.application.comment.dto.pipeline.commands.UpdateCommentCommand;
import com.prueba.CRM.application.utils.ResponseMessage;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class UpdateCommentCommandValidator extends AbstractValidator<UpdateCommentCommand> {

    /**
     * rules for UpdateCommentCommand
     */
    @Override
    public void rules() {
        ruleFor(UpdateCommentCommand::getId)
                .must(Predicate.not(ObjectPredicate.nullValue()))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_REQUIRED, "id"))
                .withFieldName("id")
                .must(ComparablePredicate.greaterThanOrEqual(0L))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_MENOR_ZERO, "id"))
                .withFieldName("id");
        ruleFor(UpdateCommentCommand::getCommentText)
                .must(Predicate.not(StringPredicate.stringEmptyOrNull()))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_REQUIRED, "commentText"))
                .withFieldName("commentText");
    }
}
