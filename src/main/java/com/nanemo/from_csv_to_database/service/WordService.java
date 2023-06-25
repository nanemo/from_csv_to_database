package com.nanemo.from_csv_to_database.service;

import com.nanemo.from_csv_to_database.dto.WordDtoString;
import com.nanemo.from_csv_to_database.entity.Word;

import java.util.Set;

public interface WordService {
    Boolean insertToDatabase(Set<WordDtoString> words);

    Boolean insert(String csvTableName);

}
