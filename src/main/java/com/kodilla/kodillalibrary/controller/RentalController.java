package com.kodilla.kodillalibrary.controller;

import com.kodilla.kodillalibrary.Rental_WrongUserOrBookCopyException;
import com.kodilla.kodillalibrary.domain.Rental;
import com.kodilla.kodillalibrary.domain.RentalDto;
import com.kodilla.kodillalibrary.mapper.RentalMapper;
import com.kodilla.kodillalibrary.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/library/rentals")
public class RentalController {
    private final DbService service;
    private final RentalMapper rentalMapper;

    @GetMapping
    public ResponseEntity<List<RentalDto>> getAllRentals(){
        return ResponseEntity.ok(rentalMapper.mapToRentalDtoList(service.getAllRentals()));
    }

    @PostMapping
    public ResponseEntity<RentalDto> createRental(@RequestBody RentalDto rentalDto) throws Rental_WrongUserOrBookCopyException, BookCopyNotAvailableException {
        Rental rental = rentalMapper.mapToRental(rentalDto);
        service.saveRental(rental);
        return ResponseEntity.ok(rentalMapper.mapToRentalDto(rental));
    }

    @DeleteMapping(value = "{rentalId}")
    public ResponseEntity<Void> deleteRental(@PathVariable Integer rentalId) throws RentalNotFoundException{
        service.deleteRental(rentalId);
        return ResponseEntity.ok().build();
    }
}
