package com.nanemo.from_csv_to_database.service.impl;

import com.nanemo.from_csv_to_database.dto.WordDtoString;
import com.nanemo.from_csv_to_database.entity.Word;
import com.nanemo.from_csv_to_database.exception.TableNotFoundException;
import com.nanemo.from_csv_to_database.repository.WordRepository;
import com.nanemo.from_csv_to_database.service.WordService;
import com.nanemo.from_csv_to_database.util.FilePath;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WordServiceImpl implements WordService {
    @Value("${file.directory}")
    private String fileDirectory;

    private final WordRepository wordRepository;
    private final FilePath filePath;

    @Override
    @Transactional
    public ResponseEntity<String> insert(String csvTableName) {
        Set<WordDtoString> words;
        try {
            words = new HashSet<>(readWordsFromCSV(csvTableName));
        } catch (FileNotFoundException e) {
            throw new TableNotFoundException("Table with this name is not found!", 404);
        }

        if (!words.isEmpty()) {
            int addedWordsSize = insertToDatabase(words);
            return addedWordsSize > 0 ? new ResponseEntity<>(addedWordsSize + " words were added to the database successfully!", HttpStatusCode.valueOf(202))
                    : new ResponseEntity<>("No one words were added to the database!", HttpStatusCode.valueOf(200));
        }
        return ResponseEntity.ok("There is not any words for adding to the database!");
    }

    private List<WordDtoString> readWordsFromCSV(String csvTableName) throws FileNotFoundException {
        String fileName = filePath.tableNameGenerator(fileDirectory, csvTableName);
        return new CsvToBeanBuilder<WordDtoString>(new FileReader(fileName))
                .withType(WordDtoString.class)
                .build()
                .parse();
    }

    private int insertToDatabase(Set<WordDtoString> wordsFromCSV) {
        Set<String> allWordsFromDatabase = wordRepository.getAllWords();

        List<Word> saved = wordRepository.saveAll(wordsFromCSV.stream().map(WordDtoString::getWord)
                .filter(s -> !(allWordsFromDatabase.contains(s)))
                .map(s -> new Word().setWord(s)).collect(Collectors.toSet()));

        return saved.size();
    }

}
