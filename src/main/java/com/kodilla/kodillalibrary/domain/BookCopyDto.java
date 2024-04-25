package com.kodilla.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BookCopyDto {
    private Integer id;
    private Integer titleId;
    private String status;
}
