package com.kodilla.kodillalibrary.service;

import com.kodilla.kodillalibrary.controller.*;
import com.kodilla.kodillalibrary.domain.*;
import com.kodilla.kodillalibrary.repository.BookCopyRepository;
import com.kodilla.kodillalibrary.repository.RentalRepository;
import com.kodilla.kodillalibrary.repository.TitleRepository;
import com.kodilla.kodillalibrary.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DbService {
    private final UserRepository userRepository;
    private final TitleRepository titleRepository;
    private final BookCopyRepository bookCopyRepository;
    private final RentalRepository rentalRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Integer id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Integer id) throws UserNotFoundException {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

    public List<Title> getAllTitles() {
        return titleRepository.findAll();
    }

    public Title getTitle(Integer id) throws TitleNotFoundException {
        return titleRepository.findById(id).orElseThrow(TitleNotFoundException::new);
    }

    public void saveTitle(Title title) {
        titleRepository.save(title);
    }

    public void deleteTitle(Integer id) throws TitleNotFoundException {
        try {
            titleRepository.deleteById(id);
        } catch (Exception e) {
            throw new TitleNotFoundException();
        }
    }

    public List<BookCopy> getAllBookCopies() {
        return bookCopyRepository.findAll();
    }

    public BookCopy getBookCopy(Integer id) throws BookCopyNotFoundException {
        return bookCopyRepository.findById(id).orElseThrow(BookCopyNotFoundException::new);
    }

    public void saveBookCopy(BookCopy bookCopy) {
        bookCopyRepository.save(bookCopy);
    }

    public void deleteBookCopy(Integer id) throws BookCopyNotFoundException {
        try {
            bookCopyRepository.deleteById(id);
        } catch (Exception e) {
            throw new BookCopyNotFoundException();
        }
    }

    public Long availableBookCopies(Integer titleId) {

        List<BookCopy> allBookCopies = bookCopyRepository.findAll();
        Long numberOfAvailableCopies = allBookCopies.stream()
                .filter(b -> b.getTitle().getId().equals(titleId))
                .filter(b -> b.getStatus().equals(Status.AVAILABLE))
                .count();
        return numberOfAvailableCopies;
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public void saveRental(Rental rental) throws BookCopyNotAvailableException {
        if (rental.getBookCopy().getStatus().equals(Status.AVAILABLE)) {
            rentalRepository.save(rental);
            bookCopyRepository.save(new BookCopy(rental.getBookCopy().getId(), rental.getBookCopy().getTitle(), Status.BOOKED));
        } else
            throw new BookCopyNotAvailableException();
    }

    public void deleteRental(Integer rental_id) throws RentalNotFoundException {
        try {
            Optional<Rental> rental = rentalRepository.findById(rental_id);
            Rental rental1 = rental.get();
            BookCopy bookCopy = rental1.getBookCopy();
            bookCopy.setStatus(Status.AVAILABLE);
            rentalRepository.deleteById(rental_id);
        } catch (Exception e) {
            throw new RentalNotFoundException();
        }
    }

}
