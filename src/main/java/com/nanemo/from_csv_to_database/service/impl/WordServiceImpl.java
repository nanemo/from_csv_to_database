package com.nanemo.from_csv_to_database.service.impl;

import com.nanemo.from_csv_to_database.entity.Word;
import com.nanemo.from_csv_to_database.repository.WordRepository;
import com.nanemo.from_csv_to_database.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {

    private WordRepository repository;

    @Override
    public boolean insertIntoDatabase() {
        return false;
    }

    @Override
    public String getWordsFromCSV() {
        return null;
    }

    @Override
    public Word getWordsFromDatabase() {
        return null;
    }
}
