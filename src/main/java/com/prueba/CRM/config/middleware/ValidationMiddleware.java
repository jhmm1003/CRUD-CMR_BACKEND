package com.prueba.CRM.config.middleware;

import an.awesome.pipelinr.Command;
import br.com.fluentvalidator.context.Error;
import br.com.fluentvalidator.context.ValidationResult;
import com.prueba.CRM.config.exceptions.CustomErrorException;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ValidationMiddleware implements Command.Middleware {
    public ValidationMiddleware() {
    }

    @Override
    @SneakyThrows
    public <R, C extends Command<R>> R invoke(C command, Next<R> next) {
        ValidationResult result = null;
        try {
            String className = command.getClass().getSimpleName();
            String packageName = command.getClass().getPackageName();
            String fullNameValidator = String.format("%1$s.%2$s", packageName, className + "Validator");
            Class<?> classValidator = Class.forName(fullNameValidator);
            Object objValidator = classValidator.getDeclaredConstructor().newInstance();

            final br.com.fluentvalidator.Validator<C> validator = (br.com.fluentvalidator.Validator<C>) objValidator;
            result = validator.validate((C) command);

        } catch (CustomErrorException e) {
            e.printStackTrace();
        } catch (RuntimeException
                | ClassNotFoundException
                | NoSuchMethodException
                | InvocationTargetException
                | InstantiationException
                | IllegalAccessException e) {
            //e.printStackTrace();
        }

        if (result == null || result.isValid()) {
            R response = next.invoke();
            return response;
        }

        List<String> errors = parseErrors(result);

        throw new CustomErrorException(HttpStatus.BAD_REQUEST, "Una o mas fallas de validacion han ocurrido", errors.toArray());
    }

    private List<String> parseErrors(ValidationResult result) {
        List<String> errors = new ArrayList<>();
        for (Error detail : result.getErrors()) {
            errors.add(detail.getMessage());
        }
        return errors;
    }
}
