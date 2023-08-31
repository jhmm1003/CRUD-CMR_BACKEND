package com.prueba.CRM.application.utils;

import br.com.fluentvalidator.context.ValidationResult;
import com.prueba.CRM.config.models.StatusResponse;

public class ValidadorUtil {

    public static void agregarMensajesError(ValidationResult result, StatusResponse response) {
        if (!result.isValid()) {
            result.getErrors().stream().forEach(error -> response.AddMessage(error.getMessage()));
        }
    }
}
