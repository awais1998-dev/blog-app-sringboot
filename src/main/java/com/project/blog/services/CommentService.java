package com.project.blog.services;

import com.project.blog.models.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComments();
    Comment createComment(Comment comment);
    void deleteComment(Long id);
}
