package com.kodilla.kodillalibrary.repository;

import com.kodilla.kodillalibrary.domain.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentalRepository extends CrudRepository<Rental,Integer> {
    List<Rental>findAll();
    Rental save(Rental rental);


}
