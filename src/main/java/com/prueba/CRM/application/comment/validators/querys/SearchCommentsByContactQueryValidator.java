package com.prueba.CRM.application.comment.validators.querys;

import br.com.fluentvalidator.AbstractValidator;
import br.com.fluentvalidator.predicate.ComparablePredicate;
import br.com.fluentvalidator.predicate.ObjectPredicate;

import com.prueba.CRM.application.comment.dto.pipeline.querys.SearchCommentsByContactQuery;
import com.prueba.CRM.application.utils.ResponseMessage;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class SearchCommentsByContactQueryValidator extends AbstractValidator<SearchCommentsByContactQuery> {

    /**
     * rules for SearchCommentByContactQuery
     */
    @Override
    public void rules() {
        ruleFor(SearchCommentsByContactQuery::getContactId)
                .must(Predicate.not(ObjectPredicate.nullValue()))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_REQUIRED, "contactId"))
                .withFieldName("contactId")
                .must(ComparablePredicate.greaterThanOrEqual(0L))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_MENOR_ZERO, "contactId"))
                .withFieldName("contactId");
    }
}
