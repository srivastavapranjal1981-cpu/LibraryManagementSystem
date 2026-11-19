
package com.example.library.controller;

import com.example.library.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> books = new ArrayList<>();

    // Q2(b) Add a new book
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {

        books.add(book);

        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    // Q2(c) Get all books
    @GetMapping
    public List<Book> getAllBooks() {
        return books;
    }

    // Q2(c) Get book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {

        for (Book b : books) {
            if (b.getId().equals(id)) {
                return ResponseEntity.ok(b);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // Q2(d) Update book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable Integer id,
            @RequestBody Book updatedBook) {

        for (Book b : books) {
            if (b.getId().equals(id)) {

                b.setTitle(updatedBook.getTitle());
                b.setAuthor(updatedBook.getAuthor());
                b.setCategory(updatedBook.getCategory());
                b.setAvailableCopies(updatedBook.getAvailableCopies());

                return ResponseEntity.ok(b);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // Q2(e) Delete book
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id) {

        for (Book b : books) {
            if (b.getId().equals(id)) {

                books.remove(b);

                return ResponseEntity.ok("Book deleted successfully!");
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Book not found!");
    }
}
