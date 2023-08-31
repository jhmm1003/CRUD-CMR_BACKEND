package com.prueba.CRM.config.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatusResponse implements Serializable {
    private Boolean success;
    private List<String> messages;
    private Object data;
    private int code = 200;
    private String status = "OK";

    public StatusResponse() {
        this(false, null, null, HttpStatus.OK);
    }

    public StatusResponse(Boolean success) {
        this(success, null, null, HttpStatus.OK);
    }

    public StatusResponse(Boolean success, String message) {
        this(success, message, null, HttpStatus.OK);
    }

    public StatusResponse(Boolean success, String message, Object data) {
        this(success, message, data, HttpStatus.OK);
    }

    public StatusResponse(Boolean success, String message, Object data, HttpStatus status) {
        this.success = success;
        if (message == null || message.length() == 0)
            this.messages = new ArrayList<>();
        else
            this.messages = new ArrayList<>(Arrays.asList(message));

        this.data = data;
        this.code = status.value();
        this.status = status.name();
    }

    public void AddMessage(String message) {
        if (!(message == null || message.length() == 0)) {
            if (!this.messages.contains(message)) {
                this.messages.add(message);
            }
        }
    }

    public void Clear() {
        this.messages.clear();
    }

}
