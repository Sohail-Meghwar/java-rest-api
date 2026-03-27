package com.api.book.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.entities.Book;
import com.api.book.services.BookService;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/book")
    public ResponseEntity<List<Book>> getbook() {
        List<Book> list = bookService.getAllbooks();
        if (list.size() <= 0) {
            return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();

        }
        return ResponseEntity.of(Optional.of(list));
        // return this.bookService.getAllbooks();

    }

    // get single book by id
    @GetMapping("/book/{id}")
    public Book getBookByid(int id) {
        Book book = null;
        book = this.bookService.getAllbooks().stream().filter(e -> e.getId() == id).findFirst().get();
        return book;
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {
        this.bookService.addBook(book);
        return book;

    }

    // delete book
    @DeleteMapping("/books/{bookid}")
    public Book deleteBook(@PathVariable("bookid") int id) {
        Book book = this.bookService.getBookById(id);
        // Implementation for deleting the book
        this.bookService.deleteBook(id);
        return book;
    }

    // update book
    @PostMapping("/books/{bookId}")
    public Book updateBook(@RequestBody Book book, @PathVariable("bookId") int bookId) {

        this.bookService.updateBook(book, bookId);
        return book;
    }
}
