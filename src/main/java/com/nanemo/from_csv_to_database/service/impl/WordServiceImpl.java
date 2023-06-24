package com.nanemo.from_csv_to_database.service.impl;

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
    public Boolean insertToDatabase(Set<Word> words) {
        return words.stream().map(word -> {
            int count = 0;
            if (repository.findByWord(word.getWord()).isEmpty()) {
                repository.save(word);
                count++;
            }
            return count > 0;
        }).findFirst().isPresent();
    }

    @Override
    public Boolean insert(String csvTableName) {
        Set<Word> words;
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

    private List<Word> readWordsFromCSV(String csvTableName) throws FileNotFoundException {
        return new CsvToBeanBuilder<Word>(new FileReader(tableNameGenerator(csvTableName)))
                .withType(Word.class)
                .build()
                .parse();
    }


    private String tableNameGenerator(String csvTableName) {
        return String.format("%s%s.csv", fileDirectory, csvTableName);
    }

}
