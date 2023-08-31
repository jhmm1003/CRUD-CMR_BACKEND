package com.prueba.CRM.application.comment.validators.querys;

import br.com.fluentvalidator.AbstractValidator;
import br.com.fluentvalidator.predicate.ComparablePredicate;
import br.com.fluentvalidator.predicate.ObjectPredicate;
import com.prueba.CRM.application.comment.dto.pipeline.querys.SearchCommentByIdQuery;
import com.prueba.CRM.application.utils.ResponseMessage;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class SearchCommentByIdQueryValidator extends AbstractValidator<SearchCommentByIdQuery> {

    /**
     * rules for SearchCommentByIdQuery
     */
    @Override
    public void rules() {
        ruleFor(SearchCommentByIdQuery::getId)
                .must(Predicate.not(ObjectPredicate.nullValue()))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_REQUIRED, "id"))
                .withFieldName("id")
                .must(ComparablePredicate.greaterThanOrEqual(0L))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_MENOR_ZERO, "id"))
                .withFieldName("id");
    }
}
