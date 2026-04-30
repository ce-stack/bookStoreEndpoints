package com.example.demo.controllers;

import com.example.demo.dto.CommentRequest;
import com.example.demo.dto.RatingRequest;
import com.example.demo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comment")
public class UserCustomController {

    private UserService userService;

    public UserCustomController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public String userAddCommentToBook(@Valid @RequestBody CommentRequest request){
        return this.userService.userCommentToBook(request);
    }

    @PostMapping("/add_rating")
    public String userAddRatingToBook(@Valid @RequestBody RatingRequest request) {
        return this.userService.userRatingToBook(request);
    }
}
