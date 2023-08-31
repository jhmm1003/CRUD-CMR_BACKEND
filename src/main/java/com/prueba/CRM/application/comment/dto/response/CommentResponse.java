package com.prueba.CRM.application.comment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponse implements Serializable {
    private Long id;
    private String commentText;
    private String commentCreationDate;
}