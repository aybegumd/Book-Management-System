package com.project.booksapp.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "publishers")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;

        @OneToOne(mappedBy = "publisher", fetch = FetchType.EAGER)
        private Book book;

}
