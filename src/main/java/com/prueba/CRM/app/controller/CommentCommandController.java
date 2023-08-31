package com.prueba.CRM.app.controller;

import an.awesome.pipelinr.Pipeline;
import com.prueba.CRM.application.comment.dto.pipeline.commands.AddCommentCommand;
import com.prueba.CRM.application.comment.dto.pipeline.commands.DeleteCommentCommand;
import com.prueba.CRM.application.comment.dto.pipeline.commands.UpdateCommentCommand;
import com.prueba.CRM.application.comment.dto.pipeline.querys.SearchCommentByIdQuery;
import com.prueba.CRM.application.comment.dto.pipeline.querys.SearchCommentsByContactQuery;
import com.prueba.CRM.config.models.StatusResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/comment")
public class CommentCommandController {
    @Autowired
    private Pipeline pipeline;

    @PostMapping
    public ResponseEntity<StatusResponse> addComment(@RequestBody AddCommentCommand command) {
        StatusResponse response = this.pipeline.send(command);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @PutMapping
    public ResponseEntity<StatusResponse> updateComment(@RequestBody UpdateCommentCommand command) {
        StatusResponse response = this.pipeline.send(command);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<StatusResponse> deleteComment(@RequestParam Long commentId) {
        DeleteCommentCommand command = new DeleteCommentCommand(commentId);
        StatusResponse response = this.pipeline.send(command);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<StatusResponse> searchCommentById(@RequestParam Long commentId) {
        SearchCommentByIdQuery query = new SearchCommentByIdQuery(commentId);
        StatusResponse response = this.pipeline.send(query);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping("/comments/{contactId}")
    public ResponseEntity<StatusResponse> searchCommentsByContact(@RequestParam Long contactId) {
        SearchCommentsByContactQuery query = new SearchCommentsByContactQuery(contactId);
        StatusResponse response = this.pipeline.send(query);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }
}
