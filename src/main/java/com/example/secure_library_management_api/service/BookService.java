package com.example.secure_library_management_api.service;

import com.example.secure_library_management_api.model.entity.Book;
import com.example.secure_library_management_api.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
@AllArgsConstructor
@Slf4j
public class BookService {

    private final BookRepository repository;

    public Book createBook(Book book)
    {
        log.info(book.getTitle());
        return repository.save(book);
    }


    //get by id
    public Optional<Book> getById(Long id)
    {
        return repository.findById(id);
    }


    //get all
    public List<Book> getAll()
    {
        return repository.findAll();
    }


    //update
    public Book update(Long id,Book updated)
    {
        log.info(updated.getTitle());
        Book book=repository.findById(id).orElseThrow(()->new EntityNotFoundException("Not Found"));
        book.setTitle(updated.getTitle());
        book.setAuthor(updated.getAuthor());
        book.setAvailableCopies(updated.getAvailableCopies());
        return repository.save(book);
    }


    //delete
    public void delete(Long id)
    {
        Book book=repository.findById(id).orElseThrow(()->new EntityNotFoundException("Not found with id: "+id));
        repository.delete(book);
    }


}
