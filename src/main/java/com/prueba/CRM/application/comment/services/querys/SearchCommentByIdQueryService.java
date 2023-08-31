package com.prueba.CRM.application.comment.services.querys;

import com.prueba.CRM.application.comment.dto.pipeline.querys.SearchCommentByIdQuery;
import com.prueba.CRM.application.comment.dto.response.CommentResponse;
import com.prueba.CRM.application.utils.Constants;
import com.prueba.CRM.application.utils.ResponseMessage;
import com.prueba.CRM.application.utils.Tools;
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
public class SearchCommentByIdQueryService {
    @Autowired
    private CommentRepository commentRepository;

    public StatusResponse searchCommentById(SearchCommentByIdQuery query) {
        Optional<Comment> commentOptional = commentRepository.findById(query.getId());
        if (!commentOptional.isPresent()) {
            throw new CustomDataNotFoundException(
                    String.format(ResponseMessage.MESSAGE_COMMENT_ID_NOT_EXISTS, query.getId())
            );
        }
        Comment comment = commentOptional.get();
        CommentResponse response = new CommentResponse(
                comment.getId(), comment.getCommentText(),
                Tools.localDateTimeToString(comment.getCommentCreationDate(), Constants.LOCAL_DATE_TIME_FORMAT));
        return new StatusResponse(Boolean.TRUE,"Comment found", response, HttpStatus.OK);
    }
}
