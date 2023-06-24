package com.nanemo.from_csv_to_database.service;

import com.nanemo.from_csv_to_database.entity.Word;

import java.util.Set;

public interface WordService {
    Boolean insertToDatabase(Set<Word> words);

    Boolean insert(String csvTableName);

}
