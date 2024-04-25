package com.kodilla.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TitleDto {
    private Integer id;
    private String title;
    private String author;
    private int publicationDate;
}
