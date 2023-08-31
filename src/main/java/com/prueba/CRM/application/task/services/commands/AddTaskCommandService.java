package com.prueba.CRM.application.task.services.commands;

import com.prueba.CRM.application.task.dto.pipeline.commands.AddTaskCommand;
import com.prueba.CRM.application.utils.Constants;
import com.prueba.CRM.application.utils.ResponseMessage;
import com.prueba.CRM.application.utils.Tools;
import com.prueba.CRM.config.exceptions.CustomDataNotFoundException;
import com.prueba.CRM.config.models.StatusResponse;
import com.prueba.CRM.domain.Contact;
import com.prueba.CRM.domain.Task;
import com.prueba.CRM.infrastructure.repository.ContactRepository;
import com.prueba.CRM.infrastructure.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class AddTaskCommandService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private TaskRepository taskRepository;

    public StatusResponse addTask(AddTaskCommand command) {
        Optional<Contact> contactOptional = contactRepository.findById(command.getContactId());
        if (!contactOptional.isPresent()) {
            throw new CustomDataNotFoundException(
                    String.format(ResponseMessage.MESSAGE_CONTACT_ID_NOT_EXISTS, command.getContactId())
            );
        }
        Optional<Contact> responsiblePersonOptional = contactRepository.findById(command.getResponsiblePerson());
        if (!responsiblePersonOptional.isPresent()) {
            throw new CustomDataNotFoundException(
                    String.format(ResponseMessage.MESSAGE_CONTACT_ID_NOT_EXISTS, command.getContactId())
            );
        }
        Task task = new Task(
                command.getTitle(), responsiblePersonOptional.get(),
                Tools.stringToLocalDateTime(command.getDeadlineDate(), Constants.LOCAL_DATE_TIME_FORMAT),
                command.isTaskStatusSummary(), contactOptional.get());
        taskRepository.save(task);
        return new StatusResponse(Boolean.TRUE,"Saved task",null, HttpStatus.OK);
    }
}
