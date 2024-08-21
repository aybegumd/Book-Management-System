package com.project.booksapp.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDate;

@Entity
@Data
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private LocalDate publishedDate;
    private String ISBN;


    @ManyToOne
    @JoinColumn(name = "author_id") //lazyfetch eagerfetch
    private Author author;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private boolean available;

    @OneToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;


}
