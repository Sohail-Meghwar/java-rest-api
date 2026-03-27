package com.api.book.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.api.book.entities.Book;

@Component
public class BookService {

    private static List<Book> list = new ArrayList<>();

    static {
        list.add(new Book(2, "java", "lajlfa"));
        list.add(new Book(3, "java", "lajlfa"));
        list.add(new Book(4, "java", "lajlfa"));

    }

    // get all books
    public List<Book> getAllbooks() {
        return list;
    }

    // get single book by id;
    public Book getBookById(int id) {

        Book book = null;
        book = list.stream().filter(e -> e.getId() == id).findFirst().get();
        return book;
    }

    // addthing the book
    public void addBook(Book b) {
        list.add(b);
    }

    // delete book
    public void deleteBook(int id) {

        list = list.stream().filter(book -> book.getId() != id).collect(Collectors.toList());

    }

    // update book
    public void updateBook(Book book, int bookId) {

        list.stream().map(b -> {
            if (b.getId() == bookId) {
                b.setTitle(book.getTitle());
                b.setAuthor(book.getAuthor());
            }
            return b;
        }).collect(Collectors.toList());

    }
}