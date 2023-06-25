package com.nanemo.from_csv_to_database.service.impl;

import com.nanemo.from_csv_to_database.dto.WordDtoString;
import com.nanemo.from_csv_to_database.entity.Word;
import com.nanemo.from_csv_to_database.exception.NotFoundException;
import com.nanemo.from_csv_to_database.repository.WordRepository;
import com.nanemo.from_csv_to_database.service.WordService;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {
    @Value("${file.directory}")
    private String fileDirectory;

    private final WordRepository repository;

    @Override
    public Boolean insertToDatabase(Set<WordDtoString> wordsFromCSV) {
        Set<Word> newWordsForContain = new HashSet<>();
        int count = 0;
        Set<String> allWordsFromDatabase = new HashSet<>(repository.getAllWords());

        for (WordDtoString word : wordsFromCSV) {
            if (!allWordsFromDatabase.contains(word.getWord())) {
                newWordsForContain.add(new Word().setWord(word.getWord()));
                count++;
            }
        }

        if (count > 0) {
            repository.saveAll(newWordsForContain);
            return true;
        }
        return false;
    }

    @Override
    public Boolean insert(String csvTableName) {
        Set<WordDtoString> words;
        try {
            words = new HashSet<>(readWordsFromCSV(csvTableName));
        } catch (FileNotFoundException e) {
            throw new NotFoundException(e.getMessage(), 404);
        }

        if (!words.isEmpty()) {
            return insertToDatabase(words);
        }
        return false;
    }

    private List<WordDtoString> readWordsFromCSV(String csvTableName) throws FileNotFoundException {
        String fileName = tableNameGenerator(csvTableName);
        return new CsvToBeanBuilder<WordDtoString>(new FileReader(fileName))
                .withType(WordDtoString.class)
                .build()
                .parse();
    }


    private String tableNameGenerator(String csvTableName) {
        return String.format("%s%s.csv", fileDirectory, csvTableName);
    }

}
