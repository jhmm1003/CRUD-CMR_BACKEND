package com.prueba.CRM.app.controller;

import an.awesome.pipelinr.Pipeline;
import com.prueba.CRM.application.task.dto.pipeline.commands.AddTaskCommand;
import com.prueba.CRM.application.task.dto.pipeline.commands.DeleteTaskCommand;
import com.prueba.CRM.application.task.dto.pipeline.commands.UpdateTaskCommand;
import com.prueba.CRM.application.task.dto.pipeline.querys.SearchTaskByIdQuery;
import com.prueba.CRM.application.task.dto.pipeline.querys.SearchTasksByContactQuery;
import com.prueba.CRM.config.models.StatusResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/task")
public class TaskCommandController {
    @Autowired
    private Pipeline pipeline;

    @PostMapping
    public ResponseEntity<StatusResponse> addTask(@RequestBody AddTaskCommand command) {
        StatusResponse response = this.pipeline.send(command);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @PutMapping
    public ResponseEntity<StatusResponse> updateTask(@RequestBody UpdateTaskCommand command) {
        StatusResponse response = this.pipeline.send(command);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<StatusResponse> deleteTask(@RequestParam Long taskId) {
        DeleteTaskCommand command = new DeleteTaskCommand(taskId);
        StatusResponse response = this.pipeline.send(command);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<StatusResponse> searchTaskById(@RequestParam Long taskId) {
        SearchTaskByIdQuery query = new SearchTaskByIdQuery(taskId);
        StatusResponse response = this.pipeline.send(query);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping("/tasks/{contactId}")
    public ResponseEntity<StatusResponse> searchTasksByContact(@RequestParam Long contactId) {
        SearchTasksByContactQuery query = new SearchTasksByContactQuery(contactId);
        StatusResponse response = this.pipeline.send(query);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }
}
