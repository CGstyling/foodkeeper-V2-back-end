package com.example.demo.service;

import com.example.demo.model.Comment;
import com.example.demo.model.Recipe;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private final CommentRepository commentRepository;
    private final RecipeRepository recipeRepository;
    public CommentService(CommentRepository commentRepository, RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
        this.commentRepository = commentRepository;
    }

    //get all comments
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
    //get comment by id
    public Optional<Comment> getCommentById(Long commentId) {
        if(!commentRepository.existsById(commentId)) {
            throw new RuntimeException("Comment not found");
        }
        return commentRepository.findById(commentId);
    }

    //add comment
    public Comment addComment(Comment comment) {
        comment = commentRepository.save(comment);
        return(comment);
    }

    //delete comment
    public void deleteComment(Long commentId) {
        if(!commentRepository.existsById(commentId)){
            throw new RuntimeException("comment not found");
        } else {
            commentRepository.deleteById(commentId);
        }
    }

}
