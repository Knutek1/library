package com.kodilla.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
@AllArgsConstructor
@Getter
public class UserDto {
    private Integer id;
    private String name;
    private String surname;
    private LocalDate dateOfJoining;

}
