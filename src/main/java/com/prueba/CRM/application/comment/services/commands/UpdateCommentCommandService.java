package com.prueba.CRM.application.comment.services.commands;

import com.prueba.CRM.application.comment.dto.pipeline.commands.UpdateCommentCommand;
import com.prueba.CRM.application.utils.ResponseMessage;
import com.prueba.CRM.config.exceptions.CustomDataNotFoundException;
import com.prueba.CRM.config.models.StatusResponse;
import com.prueba.CRM.domain.Comment;
import com.prueba.CRM.infrastructure.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class UpdateCommentCommandService {

    @Autowired
    private CommentRepository commentRepository;

    public StatusResponse updateComment(UpdateCommentCommand command) {
        Optional<Comment> commentOptional = commentRepository.findById(command.getId());
        if (!commentOptional.isPresent()) {
            throw new CustomDataNotFoundException(
                    String.format(ResponseMessage.MESSAGE_COMMENT_ID_NOT_EXISTS, command.getId())
            );
        }
        commentOptional.get().updateComment(command.getCommentText());
        Comment comment = commentOptional.get();
        commentRepository.save(comment);
        return new StatusResponse(Boolean.TRUE,"Updated comment",null, HttpStatus.OK);
    }
}
