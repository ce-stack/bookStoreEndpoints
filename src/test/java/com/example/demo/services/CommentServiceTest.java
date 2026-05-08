package com.example.demo.services;


import com.example.demo.dto.request.CommentRequest;
import com.example.demo.models.Comment;
import com.example.demo.payload.response.ApiResponse;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.UserRepositoryCustom;
import com.example.demo.security.JwtUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserRepositoryCustom userRepositoryCustom;

    @InjectMocks
    private UserService userService;

    @Mock
    private JwtUtils jwtUtils;

    @Test
    void user_can_add_comment() {
        //arrange
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setComment_value("nice book!");
        commentRequest.setUser_id(1);
        commentRequest.setBook_id(20000);

        //act
        ApiResponse apiResponse = userService.userCommentToBook(commentRequest);

        //assert
        assertNotNull(apiResponse);
        assertTrue(apiResponse.getSuccess());

        //captor
        ArgumentCaptor<Comment> commentArgumentCaptor = ArgumentCaptor.forClass(Comment.class);
        verify(userRepositoryCustom).addCommentToBook(commentArgumentCaptor.capture());

        Comment savedComment = commentArgumentCaptor.getValue();

        assertEquals("nice book!" , savedComment.getComment_value());
        assertEquals(1 ,savedComment.getUser().getId());
        assertEquals(20000 , savedComment.getBook().getId());



    }
}
