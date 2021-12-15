package com.example.demo.service;

import com.example.demo.FoodkeeperApplication;
import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ContextConfiguration(classes={FoodkeeperApplication.class})
public class CommentServiceIntegrationTest {


    @InjectMocks
    private CommentService commentService;

    @Mock
    Comment comment;

    @Mock
    private CommentRepository commentRepository;

    @Captor
    ArgumentCaptor<Comment> commentCaptor;

    @Test
    void testAddComment() {
        //ARRANGE
        comment = new Comment();
        comment.setComment("het was niet lekker");

        commentRepository.save(comment);

        //ACT
        verify(commentRepository, times(1)).save(commentCaptor.capture());
        var commentNietLekker = commentCaptor.getValue();

        //ASSERT
        assertThat(commentNietLekker.getComment()).isEqualTo("het was niet lekker");
    }

    @Test
    public void testGetCommentById() {
        //ARRANGE
        comment = new Comment();
        comment.setCommentId(1L);

        //ACT
        Mockito.when(commentRepository.existsById(1L)).thenReturn(true);
        Long expectedCommnetId = 1L;

        //ASSERT
        commentService.getCommentById(1L);
        assertEquals(expectedCommnetId, comment.getCommentId());
    }

    @Test
    public void testDeleteComment() {
        //ARRANGE
        comment = new Comment();
        comment.setCommentId(2L);

        commentRepository.delete(comment);

        //ACT
        Mockito.when(commentRepository.existsById(2L)).thenReturn(true);

        commentService.deleteComment(2L);

        //ASSERT
        verify(commentRepository, times(1)).delete(comment);
    }

}
