package com.kodilla.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class RentalDto {
    private Integer id;
    private Integer bookCopyId;
    private Integer userId;
    private LocalDate rentalDate;
    private LocalDate returnDate;
}
