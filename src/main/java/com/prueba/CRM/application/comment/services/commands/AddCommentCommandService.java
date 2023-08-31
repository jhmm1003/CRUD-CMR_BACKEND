package com.prueba.CRM.application.comment.services.commands;

import com.prueba.CRM.application.comment.dto.pipeline.commands.AddCommentCommand;
import com.prueba.CRM.application.utils.ResponseMessage;
import com.prueba.CRM.config.exceptions.CustomDataNotFoundException;
import com.prueba.CRM.config.models.StatusResponse;
import com.prueba.CRM.domain.Comment;
import com.prueba.CRM.domain.Contact;
import com.prueba.CRM.infrastructure.repository.CommentRepository;
import com.prueba.CRM.infrastructure.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class AddCommentCommandService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private CommentRepository commentRepository;

    public StatusResponse addComment(AddCommentCommand command) {
        Optional<Contact> contactOptional = contactRepository.findById(command.getContactId());
        if (!contactOptional.isPresent()) {
            throw new CustomDataNotFoundException(
                    String.format(ResponseMessage.MESSAGE_CONTACT_ID_NOT_EXISTS, command.getContactId())
            );
        }
        Comment comment = new Comment(command.getCommentText(), contactOptional.get());
        commentRepository.save(comment);
        return new StatusResponse(Boolean.TRUE,"Saved comment",null, HttpStatus.OK);
    }
}
