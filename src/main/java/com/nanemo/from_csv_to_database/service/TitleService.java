package com.nanemo.from_csv_to_database.service;

import org.springframework.http.ResponseEntity;

public interface TitleService {

    ResponseEntity<String> insert(String csvTableName);
}
