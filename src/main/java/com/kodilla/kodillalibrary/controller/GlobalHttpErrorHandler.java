package com.kodilla.kodillalibrary.controller;

import com.kodilla.kodillalibrary.Rental_WrongUserOrBookCopyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserException(UserNotFoundException userNotFoundException) {
        return new ResponseEntity<>("User with given Id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TitleNotFoundException.class)
    public ResponseEntity<Object> handleTitleException(TitleNotFoundException titleNotFoundException) {
        return new ResponseEntity<>("Title with given Id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookCopyNotFoundException.class)
    public ResponseEntity<Object> handleBookCopyException(BookCopyNotFoundException bookCopyNotFoundException) {
        return new ResponseEntity<>("Book copy with given Id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookCopy_WrongTitleOrStatusException.class)
    public ResponseEntity<Object> handleBookCopyWrongTitleOrStatusException(BookCopy_WrongTitleOrStatusException bookCopies_wrongTitleOrStatusException) {
        return new ResponseEntity<>("Wrong Title Id or Status (acceptable values: AVAILABLE, BOOKED or BORROWED)", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RentalNotFoundException.class)
    public ResponseEntity<Object> handleRentalException(RentalNotFoundException rentalNotFoundException){
        return new ResponseEntity<>("Rental with given Id doesn't Exist",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Rental_WrongUserOrBookCopyException.class)
    public ResponseEntity<Object> handleRentalWrongUserOrBookCopyException(Rental_WrongUserOrBookCopyException rental_wrongUserOrBookCopyException){
        return new ResponseEntity<>("Wrong User Id or Bookcopy Id",HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BookCopyNotAvailableException.class)
    public ResponseEntity<Object>handleBookCopyNotAvailableException(BookCopyNotAvailableException bookCopyNotAvailableException){
        return new ResponseEntity<>("This book copy is currently unavailable",HttpStatus.BAD_REQUEST);
    }
}
