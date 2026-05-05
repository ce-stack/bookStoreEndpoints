package com.example.demo.controllers;

import com.example.demo.dto.request.BuyBookRequest;
import com.example.demo.dto.request.CommentRequest;
import com.example.demo.dto.request.RatingRequest;
import com.example.demo.dto.request.UpdateCommentRequest;
import com.example.demo.models.Book;
import com.example.demo.payload.response.ApiListResponse;
import com.example.demo.payload.response.ApiResponse;
import com.example.demo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("comment")
public class UserCustomController {

    private UserService userService;

    public UserCustomController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ApiResponse userAddCommentToBook(@Valid @RequestBody CommentRequest request){
        return this.userService.userCommentToBook(request);
    }

    @PostMapping("/add_rating")
    public ApiResponse userAddRatingToBook(@Valid @RequestBody RatingRequest request) {
        return this.userService.userRatingToBook(request);
    }

    @PostMapping("/update_comment")
    public ApiResponse userUpdateComment(@Valid @RequestBody UpdateCommentRequest request) {
        return this.userService.userUpdateComment(request , request.getId());
    }

    @PostMapping("/search_book")
    public List<Book> searchBooks(@RequestBody Map<String , String> body) {
        return this.userService.searchBook(body.get("value"));
    }

    @GetMapping("/all_books")
    public ApiListResponse allBooks(@RequestParam(defaultValue = "0")int page , @RequestParam(defaultValue = "10") int size ) {
        return this.userService.allBooks(page , size);
    }

    @PostMapping("/buy_book")
    public ApiResponse bookByUser(@Valid @RequestBody BuyBookRequest request) {
        return this.userService.userBuyBook(request);
    }
}
