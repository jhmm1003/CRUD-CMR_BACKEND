package com.prueba.CRM.application.comment.services.commands;

import com.prueba.CRM.application.comment.dto.pipeline.commands.DeleteCommentCommand;
import com.prueba.CRM.application.utils.ResponseMessage;
import com.prueba.CRM.config.exceptions.CustomDataNotFoundException;
import com.prueba.CRM.config.models.StatusResponse;
import com.prueba.CRM.infrastructure.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DeleteCommentCommandService {
    @Autowired
    private CommentRepository commentRepository;

    public StatusResponse deleteComment(DeleteCommentCommand command) {
        Boolean existsComment = commentRepository.existsById(command.getId());
        if (Boolean.FALSE.equals(existsComment)) {
            throw new CustomDataNotFoundException(
                    String.format(ResponseMessage.MESSAGE_COMMENT_ID_NOT_EXISTS, command.getId())
            );
        }
        commentRepository.deleteById(command.getId());
        return new StatusResponse(Boolean.TRUE, "Deleted comment", null, HttpStatus.OK);
    }
}
