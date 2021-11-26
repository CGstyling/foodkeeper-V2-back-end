package com.example.demo.repository;

import com.example.demo.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByRecipeId(Long recipeId);
    List<Comment> findAllByUserId(Long userId);
    List<Comment> findCommentByRecipeIdAndUserId(Long recipeId, Long userId);

}
