package com.kodilla.kodillalibrary.mapper;

import com.kodilla.kodillalibrary.controller.BookCopy_WrongTitleOrStatusException;
import com.kodilla.kodillalibrary.domain.BookCopy;
import com.kodilla.kodillalibrary.domain.BookCopyDto;
import com.kodilla.kodillalibrary.domain.Status;
import com.kodilla.kodillalibrary.domain.Title;
import com.kodilla.kodillalibrary.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookCopiesMapper {
    private final TitleRepository titleRepository;

    public BookCopy mapToBookCopies(BookCopyDto bookCopyDto) throws BookCopy_WrongTitleOrStatusException {
        try {
            Optional<Title> title = titleRepository.findById(bookCopyDto.getTitleId());
            Title title1 = title.get();
            if (isStatusEnumValue(bookCopyDto.getStatus())) {
                return new BookCopy(bookCopyDto.getId(), title1, Status.valueOf(bookCopyDto.getStatus()));
            } else
                throw new BookCopy_WrongTitleOrStatusException();
        } catch (Exception e) {
            throw new BookCopy_WrongTitleOrStatusException();
        }
    }

    public BookCopyDto mapToBookCopiesDto(BookCopy bookCopy) {
        return new BookCopyDto(bookCopy.getId(), bookCopy.getTitle().getId(), bookCopy.getStatus().name());
    }

    public List<BookCopyDto> mapToBookCopiesDtoList(List<BookCopy> bookCopiesList) {
        return bookCopiesList.stream().map(this::mapToBookCopiesDto).toList();
    }

    public boolean isStatusEnumValue(String string) {
        for (Status s : Status.values()) {
            if (s.name().equals(string)) {
                return true;
            }
        }
        return false;
    }


}
