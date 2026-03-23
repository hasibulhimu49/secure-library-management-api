package com.example.secure_library_management_api.controller;

import com.example.secure_library_management_api.model.entity.Book;
import com.example.secure_library_management_api.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
@Slf4j
public class BookController {

    private final BookService service;


    //@PreAuthorize (Method-level authorization). এটি directly controller/service method এর সামনে rule enforce করে।
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Book createBook(@RequestBody Book book)
    {
        log.info(book.getTitle());

        return service.createBook(book);
    }


    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/all")
    public List<Book> getAll()
    {
        return service.getAll();
    }


    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}")
    public Optional<Book> getById(@PathVariable Long id)
    {
        return service.getById(id);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Book update(@PathVariable Long id,@RequestBody Book updatebook)
    {
        return service.update(id,updatebook);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id)
    {
        service.delete(id);
        return "Delete successfully";
    }
}
