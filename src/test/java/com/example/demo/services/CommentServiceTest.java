package com.example.demo.services;


import com.example.demo.dto.request.BuyBookRequest;
import com.example.demo.dto.request.CommentRequest;
import com.example.demo.dto.request.UpdateCommentRequest;
import com.example.demo.models.Book;
import com.example.demo.models.Comment;
import com.example.demo.models.User;
import com.example.demo.payload.response.ApiResponse;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.UserRepositoryCustom;
import com.example.demo.security.JwtUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserRepositoryCustom userRepositoryCustom;

    @Mock
    private BookRepository bookRepository;

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

    @Test
    void user_can_update_comment() {
        //arrange
        UpdateCommentRequest updateCommentRequest = new UpdateCommentRequest();
        updateCommentRequest.setComment_value("updated comment!");
        updateCommentRequest.setId(1);
        Comment existComment = new Comment();
        existComment.setId(1);
        existComment.setComment_value("old comment!");
        when(userRepositoryCustom.findCommentById(1)).thenReturn(existComment);

        //act
        ApiResponse apiResponse = userService.userUpdateComment(updateCommentRequest, updateCommentRequest.getId());

        //assert
        assertNotNull(apiResponse);
        
        assertEquals("updated comment!" , existComment.getComment_value());
        verify(userRepositoryCustom).findCommentById(1);
    }

    @Test
    void user_can_buy_book() {

        //arrange
        Book book = new Book();
        book.setId(1);
        book.setPrice(100);
        book.setStock(5);
        when(bookRepository.findById(1l)).thenReturn(Optional.of(book));

        BuyBookRequest buyBookRequest = new BuyBookRequest();
        buyBookRequest.setUser_id(1);
        buyBookRequest.setBook_id(1l);

        Boolean stockCount = true;

        //act
        ApiResponse apiResponse = userService.userBuyBook(buyBookRequest);

        //assert
        assertNotNull(apiResponse);
        assertEquals("you buyed book success" , apiResponse.getStatus());
        assertEquals(true , apiResponse.getSuccess());
        assertEquals(200 , apiResponse.getCode());
    }
}
