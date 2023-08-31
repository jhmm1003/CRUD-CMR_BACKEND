package com.prueba.CRM.application.task.validators.querys;

import br.com.fluentvalidator.AbstractValidator;
import br.com.fluentvalidator.predicate.ComparablePredicate;
import br.com.fluentvalidator.predicate.ObjectPredicate;
import com.prueba.CRM.application.task.dto.pipeline.querys.SearchTasksByContactQuery;
import com.prueba.CRM.application.utils.ResponseMessage;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class SearchTasksByContactQueryValidator extends AbstractValidator<SearchTasksByContactQuery> {

    /**
     * rules for SearchTasksByContactQuery
     */
    @Override
    public void rules() {
        ruleFor(SearchTasksByContactQuery::getContactId)
                .must(Predicate.not(ObjectPredicate.nullValue()))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_REQUIRED, "contactId"))
                .withFieldName("contactId")
                .must(ComparablePredicate.greaterThanOrEqual(0L))
                .withMessage(String.format(ResponseMessage.MESSAGE_FIELD_MENOR_ZERO, "contactId"))
                .withFieldName("contactId");
    }
}
