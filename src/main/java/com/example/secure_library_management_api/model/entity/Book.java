package com.example.secure_library_management_api.model.entity;

import com.example.secure_library_management_api.model.enumm.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "book_table")
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "book_name")
    @JsonProperty("book_title")
    private String title;

    @Column(name = "book_author")
    @JsonProperty("book_author")
    private String author;

    @Column(name = "book_quantity")
    @JsonProperty("book_copies")
    private Integer availableCopies;

}
