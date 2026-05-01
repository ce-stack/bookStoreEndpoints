package com.example.demo.services;

import com.example.demo.dto.CommentRequest;
import com.example.demo.dto.RatingRequest;
import com.example.demo.dto.UpdateCommentRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.models.Book;
import com.example.demo.models.Comment;
import com.example.demo.models.Rating;
import com.example.demo.models.User;
import com.example.demo.payload.response.ApiResponse;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.UserRepositoryCustom;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepositoryCustom userRepositoryCustom;
    private final BookRepository bookRepository;
    public UserService(UserRepositoryCustom userRepositoryCustom , BookRepository bookRepository)
    {
        this.userRepositoryCustom = userRepositoryCustom;
        this.bookRepository = bookRepository;
    }

    public ApiResponse userCommentToBook(CommentRequest request) {
        Comment comment = new Comment();

        comment.setComment_value(request.getComment_value());

        Book book = new Book();
        book.setId(request.getBook_id());

        User user = new User();
        user.setId(request.getUser_id());

        comment.setBook(book);
        comment.setUser(user);
        userRepositoryCustom.addCommentToBook(comment);

        return new ApiResponse<>("book added success" , true);

    }

    public ApiResponse userRatingToBook(RatingRequest request) {
        Rating rating = new Rating();

        rating.setValue(request.getValue());

        Book book = new Book();
        book.setId(request.getBook_id());

        User user = new User();
        user.setId(request.getUser_id());

        rating.setBook(book);
        rating.setUser(user);

        userRepositoryCustom.addRatingToBook(rating);

        return new ApiResponse("rating added success" , true);

    }

    @Transactional
    public ApiResponse userUpdateComment(UpdateCommentRequest request, int id) {

        Comment comment = userRepositoryCustom.findCommentById(id);

        if (comment == null) {
            throw new ResourceNotFoundException("comment not found");
        }

        comment.setComment_value(request.getComment_value());

        return new ApiResponse<>("comment updated success" , true);
    }

    public List<Book> seachBook(String value) {
        return userRepositoryCustom.SearchBook(value);
    }

    public ApiResponse allBooks(){
        List<Book> books = bookRepository.findAll();
        return new ApiResponse<>("all books" , books , true);
    }
}
