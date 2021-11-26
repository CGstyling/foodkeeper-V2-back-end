package com.example.demo.service;

import com.example.demo.model.Comment;
import com.example.demo.model.Recipe;
import com.example.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommentService {

    @Autowired
    CommentRepository commentRepository;
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    //get all comments
    public List<Comment> getAllComment() {
        List<Comment> comments = new ArrayList<>();
        commentRepository.findAll().forEach(comments::add);
        return comments;
    }

    //get comment by id
    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }
    //add comment
    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    //delete comment
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
