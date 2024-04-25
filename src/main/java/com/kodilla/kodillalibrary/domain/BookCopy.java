package com.kodilla.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "book_copies")
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    @Setter
    @OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "titleId")
    private Title title;

    @Setter
    @Column(name = "status")
    private Status status;

}
