package com.prueba.CRM.application.task.services.commands;

import com.prueba.CRM.application.task.dto.pipeline.commands.DeleteTaskCommand;
import com.prueba.CRM.application.utils.ResponseMessage;
import com.prueba.CRM.config.exceptions.CustomDataNotFoundException;
import com.prueba.CRM.config.models.StatusResponse;
import com.prueba.CRM.infrastructure.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DeleteTaskCommandService {
    @Autowired
    private TaskRepository taskRepository;

    public StatusResponse deleteTask(DeleteTaskCommand command) {
        Boolean existsTask = taskRepository.existsById(command.getId());
        if (Boolean.FALSE.equals(existsTask)) {
            throw new CustomDataNotFoundException(
                    String.format(ResponseMessage.MESSAGE_TASK_ID_NOT_EXISTS, command.getId())
            );
        }
        taskRepository.deleteById(command.getId());
        return new StatusResponse(Boolean.TRUE, "Deleted task", null, HttpStatus.OK);
    }
}
