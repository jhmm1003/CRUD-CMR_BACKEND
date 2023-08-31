package com.prueba.CRM.application.comment.services.querys;

import com.prueba.CRM.application.comment.dto.pipeline.querys.SearchCommentsByContactQuery;
import com.prueba.CRM.application.comment.dto.response.CommentResponse;
import com.prueba.CRM.application.utils.Constants;
import com.prueba.CRM.application.utils.ResponseMessage;
import com.prueba.CRM.application.utils.Tools;
import com.prueba.CRM.config.exceptions.CustomDataNotFoundException;
import com.prueba.CRM.config.models.StatusResponse;
import com.prueba.CRM.domain.Comment;
import com.prueba.CRM.infrastructure.repository.CommentRepository;
import com.prueba.CRM.infrastructure.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class SearchCommentsByContactQueryService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private CommentRepository commentRepository;

    public StatusResponse searchCommentsByContact(SearchCommentsByContactQuery query) {
        Boolean existsContact = contactRepository.existsById(query.getContactId());
        if (Boolean.FALSE.equals(existsContact)) {
            throw new CustomDataNotFoundException(
                    String.format(ResponseMessage.MESSAGE_CONTACT_ID_NOT_EXISTS, query.getContactId())
            );
        }
        Optional<List<Comment>> commentsOptional = commentRepository.searchCommentsByContact(query.getContactId());
        List<CommentResponse> commentResponses = new ArrayList<>();
        commentsOptional.get().stream().forEach(result -> commentResponses.add(commentToCommentResponse(result)));
        return new StatusResponse(Boolean.TRUE,"Comments found", commentResponses, HttpStatus.OK);
    }

    public CommentResponse commentToCommentResponse(Comment comment){
        return new CommentResponse(
                comment.getId(), comment.getCommentText(),
                Tools.localDateTimeToString(comment.getCommentCreationDate(), Constants.LOCAL_DATE_TIME_FORMAT));
    }
}
