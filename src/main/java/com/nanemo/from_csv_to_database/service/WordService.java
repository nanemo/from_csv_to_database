package com.nanemo.from_csv_to_database.service;

import com.nanemo.from_csv_to_database.entity.Word;

public interface WordService {
    boolean insertIntoDatabase();

    String getWordsFromCSV();

    Word getWordsFromDatabase();
}
