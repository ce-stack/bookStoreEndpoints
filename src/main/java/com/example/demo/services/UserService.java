package com.example.demo.services;

import com.example.demo.dto.request.BuyBookRequest;
import com.example.demo.dto.request.CommentRequest;
import com.example.demo.dto.request.RatingRequest;
import com.example.demo.dto.request.UpdateCommentRequest;
import com.example.demo.dto.response.AuthorListResponse;
import com.example.demo.dto.response.BookDetailsResponse;
import com.example.demo.dto.response.CategoryResponse;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.models.*;
import com.example.demo.payload.response.ApiListResponse;
import com.example.demo.payload.response.ApiResponse;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.UserRepositoryCustom;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepositoryCustom userRepositoryCustom;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    public UserService(UserRepositoryCustom userRepositoryCustom , BookRepository bookRepository , AuthorRepository authorRepository , CategoryRepository categoryRepository)
    {
        this.userRepositoryCustom = userRepositoryCustom;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository  = categoryRepository;
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

        return new ApiResponse("book added success" ,true ,200);

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

        return new ApiResponse("rating added success" , true , 200);

    }

    @Transactional
    public ApiResponse userUpdateComment(UpdateCommentRequest request, int id) {

        Comment comment = userRepositoryCustom.findCommentById(id);

        if (comment == null) {
            throw new ResourceNotFoundException("comment not found");
        }

        comment.setComment_value(request.getComment_value());

        return new ApiResponse("comment updated success" , true ,200);
    }

    public List<Book> searchBook(String value) {
        return userRepositoryCustom.SearchBook(value);
    }


    public ApiListResponse allBooks(int page , int size){
        Page<Book> booksPage= bookRepository.findAll(PageRequest.of(page , size));
        List<BookDetailsResponse> bookDetailsResponses = booksPage.getContent().stream().map(BookDetailsResponse::new)
                .toList();
        return new ApiListResponse<>(
                "all books",
                bookDetailsResponses,
                booksPage.getNumber(),
                booksPage.getSize(),
                booksPage.getTotalElements(),
                booksPage.getTotalPages(),
                booksPage.isLast(),
                true
        );
    }

    public ApiListResponse allAuthors(int page , int size) {
        Page<Author> authorPage = authorRepository.findAll(PageRequest.of(page , size));
        List<AuthorListResponse> authorListResponseList = authorPage.getContent().stream().map(AuthorListResponse::new)
                .toList();
        return new ApiListResponse<>(
                "All Authors",
                authorListResponseList,
                authorPage.getNumber(),
                authorPage.getSize(),
                authorPage.getTotalElements(),
                authorPage.getTotalPages(),
                authorPage.isLast(),
                true
        );
    }

    public ApiListResponse allCategories(int page , int size) {
        Page<Category> categoryPage = categoryRepository.findAll(PageRequest.of(page, size));
        List<CategoryResponse> categoryResponses = categoryPage.getContent().stream().map(CategoryResponse::new).toList();
        return new ApiListResponse<>(
                "All Categories",
                categoryResponses,
                categoryPage.getNumber(),
                categoryPage.getSize(),
                categoryPage.getTotalElements(),
                categoryPage.getTotalPages(),
                categoryPage.isLast(),
                true
        );
    }


    @Transactional
    public ApiResponse userBuyBook(BuyBookRequest request) {
        UserBook userBook = new UserBook();

        Book book = bookRepository.findById(request.getBook_id())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        User user = new User();
        user.setId(request.getUser_id());

        userBook.setBook(book);
        userBook.setUser(user);
        userBook.setPrice(book.getPrice());

        Boolean stockCount =  decreaseBookStock(book);

        if (stockCount == false) {
            return new ApiResponse("not enough stock" , false , 400);
        }

        userRepositoryCustom.userBuyBook(userBook);

        return new ApiResponse("you buyed book success" , true , 200);
    }

    private boolean decreaseBookStock(Book book) {
        int stock = book.getStock();
        if (stock != 0) {
            book.setStock(stock - 1);
            return true;
        } else if (stock == 0){
            return false;
        } else {
            return false;
        }
    }


}
