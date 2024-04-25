package com.kodilla.kodillalibrary.mapper;

import com.kodilla.kodillalibrary.Rental_WrongUserOrBookCopyException;
import com.kodilla.kodillalibrary.domain.BookCopy;
import com.kodilla.kodillalibrary.domain.Rental;
import com.kodilla.kodillalibrary.domain.RentalDto;
import com.kodilla.kodillalibrary.domain.User;
import com.kodilla.kodillalibrary.repository.BookCopyRepository;
import com.kodilla.kodillalibrary.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RentalMapper {
    private final BookCopyRepository bookCopyRepository;
    private final UserRepository userRepository;
    public Rental mapToRental(RentalDto rentalDto) throws Rental_WrongUserOrBookCopyException {
        try {
            Optional<BookCopy> bookCopy = bookCopyRepository.findById(rentalDto.getBookCopyId());
            BookCopy bookCopy1 = bookCopy.get();
            Optional<User> user = userRepository.findById(rentalDto.getUserId());
            User user1 = user.get();
            return new Rental(rentalDto.getId(), bookCopy1, user1, LocalDate.now(), LocalDate.now().plusMonths(1));
        }catch (Exception e) {
         throw new Rental_WrongUserOrBookCopyException();
        }

    }

    public RentalDto mapToRentalDto(Rental rental){
        return new RentalDto(rental.getId(),rental.getBookCopy().getId(), rental.getUser().getId(),rental.getRentalDate(),rental.getReturnDate());
    }
    public List<RentalDto>mapToRentalDtoList(List<Rental> rentalList){
       return rentalList.stream()
               .map(this::mapToRentalDto)
               .toList();
    }
}
