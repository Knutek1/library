package com.kodilla.kodillalibrary.repository;

import com.kodilla.kodillalibrary.domain.BookCopy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookCopyRepository extends CrudRepository<BookCopy,Integer> {
    List<BookCopy> findAll();
    Optional<BookCopy> findById(Integer bookCopyId);
    BookCopy save(BookCopy bookCopy);

}
