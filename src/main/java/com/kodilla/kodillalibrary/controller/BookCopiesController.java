package com.kodilla.kodillalibrary.controller;

import com.kodilla.kodillalibrary.domain.BookCopy;
import com.kodilla.kodillalibrary.domain.BookCopyDto;
import com.kodilla.kodillalibrary.domain.Title;
import com.kodilla.kodillalibrary.domain.TitleDto;
import com.kodilla.kodillalibrary.mapper.BookCopiesMapper;
import com.kodilla.kodillalibrary.mapper.TitleMapper;
import com.kodilla.kodillalibrary.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/library/book_copies")
public class BookCopiesController {
    private final DbService service;
    private final BookCopiesMapper bookCopiesMapper;
    private final TitleMapper titleMapper;

    @GetMapping
    public ResponseEntity<List<BookCopyDto>> getAllBookCopies(){
        List<BookCopy>bookCopies = service.getAllBookCopies();
        return ResponseEntity.ok(bookCopiesMapper.mapToBookCopiesDtoList(bookCopies));
    }

    @GetMapping(value = "{bookCopyId}")
    public ResponseEntity<BookCopyDto> getBookCopy(@PathVariable Integer bookCopyId) throws BookCopyNotFoundException{
        BookCopy bookCopy = service.getBookCopy(bookCopyId);
        return ResponseEntity.ok(bookCopiesMapper.mapToBookCopiesDto(bookCopy));
    }

    @DeleteMapping(value = "{bookCopyId}")
    public ResponseEntity<Void>deleteBookCopy(@PathVariable Integer bookCopyId) throws BookCopyNotFoundException{
        service.deleteBookCopy(bookCopyId);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<BookCopyDto>createBookCopy(@RequestBody BookCopyDto bookCopyDto) throws BookCopy_WrongTitleOrStatusException{
        BookCopy bookCopy = bookCopiesMapper.mapToBookCopies(bookCopyDto);
        service.saveBookCopy(bookCopy);
        return ResponseEntity.ok(bookCopiesMapper.mapToBookCopiesDto(bookCopy));
    }

    @PutMapping
    public ResponseEntity<BookCopyDto>updateBookCopy(@RequestBody BookCopyDto bookCopyDto) throws BookCopy_WrongTitleOrStatusException{
        BookCopy bookCopy = bookCopiesMapper.mapToBookCopies(bookCopyDto);
        service.saveBookCopy(bookCopy);
        return ResponseEntity.ok(bookCopiesMapper.mapToBookCopiesDto(bookCopy));
    }

    @GetMapping("/available")
    public ResponseEntity<Long>availableBookCopies(@RequestBody TitleDto titleDto){
        Title title = titleMapper.mapToTitle(titleDto);
        Long availableBookCopies = service.availableBookCopies(title.getId());
        return ResponseEntity.ok(availableBookCopies);
    }

}
