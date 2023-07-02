package com.nanemo.from_csv_to_database.controller;

import com.nanemo.from_csv_to_database.dto.TitleTextDto;
import com.nanemo.from_csv_to_database.dto.WordDto;
import com.nanemo.from_csv_to_database.service.TitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/texts")
public class TitleTextController {

    private final TitleService titleService;

    @PostMapping
    public ResponseEntity<Object> insertInTables(@RequestBody WordDto wordDto) {
        try {
            return titleService.insert(wordDto.getTableName());
        } catch (RuntimeException ex) {
            return ResponseEntity.status();
        }
    }

}
