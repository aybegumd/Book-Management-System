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
    private boolean available;


    @ManyToOne
    @JoinColumn(name = "author_id") //lazyfetch eagerfetch
    private Author author;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;


}
