package com.nanemo.from_csv_to_database.service;

import org.springframework.http.ResponseEntity;

public interface WordService {
    ResponseEntity<String> insert(String csvTableName);

}
