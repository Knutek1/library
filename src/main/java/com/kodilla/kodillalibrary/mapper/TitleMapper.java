package com.kodilla.kodillalibrary.mapper;

import com.kodilla.kodillalibrary.domain.Title;
import com.kodilla.kodillalibrary.domain.TitleDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TitleMapper {
    public Title mapToTitle(TitleDto titleDto){
        return new Title(titleDto.getId(), titleDto.getTitle(),titleDto.getAuthor(), titleDto.getPublicationDate());
    }
    public TitleDto mapToTitleDto(Title title){
        return new TitleDto(title.getId(), title.getTitle(),title.getAuthor(), title.getPublicationDate());
    }
    public List<TitleDto> mapToTitleDtoList(List<Title>titleList){
        return titleList.stream().map(this::mapToTitleDto).toList();
    }
}
