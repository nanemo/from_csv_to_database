package com.nanemo.from_csv_to_database.controller;

import com.nanemo.from_csv_to_database.dto.WordDto;
import com.nanemo.from_csv_to_database.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/words")
@RequiredArgsConstructor
public class WordController {

    private final WordService wordService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> insertTable(@RequestBody WordDto wordDto) {
        return ResponseEntity.ok(wordService.insert(wordDto.getTableName()) ?
                "Operation completed successfully" : "Something went wrong");
    }

}

