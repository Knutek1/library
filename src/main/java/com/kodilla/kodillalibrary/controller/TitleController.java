package com.kodilla.kodillalibrary.controller;

import com.kodilla.kodillalibrary.domain.Title;
import com.kodilla.kodillalibrary.domain.TitleDto;
import com.kodilla.kodillalibrary.domain.User;
import com.kodilla.kodillalibrary.domain.UserDto;
import com.kodilla.kodillalibrary.mapper.TitleMapper;
import com.kodilla.kodillalibrary.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/library/titles")
public class TitleController {
    private final DbService service;
    private final TitleMapper titleMapper;
    @GetMapping
    public ResponseEntity<List<TitleDto>> getTitles(){
        List<Title> users = service.getAllTitles();
        return ResponseEntity.ok(titleMapper.mapToTitleDtoList(users));
    }
    @GetMapping(value = "{titleId}")
    public ResponseEntity<TitleDto>getTitle(@PathVariable Integer titleId) throws TitleNotFoundException{
        return ResponseEntity.ok(titleMapper.mapToTitleDto(service.getTitle(titleId)));
    }

    @DeleteMapping(value = "{titleId}")
    public ResponseEntity<Void>deleteTitle(@PathVariable Integer titleId) throws TitleNotFoundException{
        service.deleteTitle(titleId);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<TitleDto>updateTitle(@RequestBody TitleDto titleDto){
        Title title = titleMapper.mapToTitle(titleDto);
        service.saveTitle(title);

        return ResponseEntity.ok(titleMapper.mapToTitleDto(title));
    }

    @PostMapping
    public ResponseEntity<TitleDto> createTitle(@RequestBody TitleDto titleDto) {
        Title title = titleMapper.mapToTitle(titleDto);
        service.saveTitle(title);
        return ResponseEntity.ok(titleMapper.mapToTitleDto(title));
    }
}
