package com.nanemo.from_csv_to_database.controller;

import com.nanemo.from_csv_to_database.dto.TableDto;
import com.nanemo.from_csv_to_database.service.TitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/texts")
@RequiredArgsConstructor
public class TitleTextController {

    private final TitleService titleService;

    @PostMapping
    public ResponseEntity<Object> insertInTables(@RequestBody TableDto tableDto) {
        return titleService.insert(tableDto.getTableName());

    }

}

