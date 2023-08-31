package com.prueba.CRM.application.task.services.querys;

import com.prueba.CRM.application.contact.dto.response.ContactResponse;
import com.prueba.CRM.application.task.dto.pipeline.querys.SearchTasksByContactQuery;
import com.prueba.CRM.application.task.dto.response.TaskResponse;
import com.prueba.CRM.application.utils.Constants;
import com.prueba.CRM.application.utils.ResponseMessage;
import com.prueba.CRM.application.utils.Tools;
import com.prueba.CRM.config.exceptions.CustomDataNotFoundException;
import com.prueba.CRM.config.models.StatusResponse;
import com.prueba.CRM.domain.Task;
import com.prueba.CRM.infrastructure.repository.ContactRepository;
import com.prueba.CRM.infrastructure.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class SearchTasksByContactQueryService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private TaskRepository taskRepository;

    public StatusResponse searchTasksByContact(SearchTasksByContactQuery query) {
        Boolean existsContact = contactRepository.existsById(query.getContactId());
        if (Boolean.FALSE.equals(existsContact)) {
            throw new CustomDataNotFoundException(
                    String.format(ResponseMessage.MESSAGE_CONTACT_ID_NOT_EXISTS, query.getContactId())
            );
        }
        Optional<List<Task>> tasksOptional = taskRepository.searchTasksByContact(query.getContactId());
        List<TaskResponse> taskResponses = new ArrayList<>();
        tasksOptional.get().stream().forEach(result -> taskResponses.add(taskToTaskResponse(result)));
        return new StatusResponse(Boolean.TRUE,"Tasks found", taskResponses, HttpStatus.OK);
    }

    public TaskResponse taskToTaskResponse(Task task){
        return new TaskResponse(
                task.getId(), task.getTitle(),
                new ContactResponse(task.getResponsiblePerson().getId(),
                        task.getResponsiblePerson().getName(), task.getResponsiblePerson().getLastName(), task.getResponsiblePerson().getEmail(),
                        task.getResponsiblePerson().getPhone(), Tools.localDateToString(task.getResponsiblePerson().getBirthDate(), Constants.LOCAL_DATE_FORMAT),
                        task.getResponsiblePerson().getAddress(), task.getResponsiblePerson().getTypeContact(), task.getResponsiblePerson().getOrigin()),
                Tools.localDateTimeToString(task.getDeadlineDate(), Constants.LOCAL_DATE_TIME_FORMAT),
                task.isTaskStatusSummary(), Tools.localDateTimeToString(task.getTaskCreationDate(), Constants.LOCAL_DATE_TIME_FORMAT));
    }
}
