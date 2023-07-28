package com.nanemo.from_csv_to_database.service.impl;

import com.nanemo.from_csv_to_database.dto.WordDtoString;
import com.nanemo.from_csv_to_database.entity.Word;
import com.nanemo.from_csv_to_database.exception.CsvFormatException;
import com.nanemo.from_csv_to_database.exception.ValidateFileNameException;
import com.nanemo.from_csv_to_database.repository.WordRepository;
import com.nanemo.from_csv_to_database.service.WordService;
import com.nanemo.from_csv_to_database.util.FilePath;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
        words = new HashSet<>(readWordsFromCSV(csvTableName));
        if (!words.isEmpty()) {
            int addedWordsSize = insertToDatabase(words);
            return addedWordsSize > 0 ? new ResponseEntity<>(addedWordsSize +
                    " words were added to the database successfully!", HttpStatusCode.valueOf(202))
                    : new ResponseEntity<>("You can not insert duplicate words into data!"
                    , HttpStatusCode.valueOf(200));
        }

        return ResponseEntity.ok("There is not any words for adding to the database!");
    }

    private List<WordDtoString> readWordsFromCSV(String csvTableName) {
        String fileName = filePath.tableNameGenerator(fileDirectory, csvTableName);
        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {
            CsvToBean<WordDtoString> csvToBean = new CsvToBeanBuilder<WordDtoString>(csvReader)
                    .withType(WordDtoString.class)
                    .build();

            List<WordDtoString> wordToList = new ArrayList<>();

            for (WordDtoString wordDtoString : csvToBean) {
                if (wordDtoString.getAnotherColumn() != null && !wordDtoString.getAnotherColumn().isEmpty()) {
                    throw new CsvFormatException("CSV file must has single columns");
                }
                wordToList.add(wordDtoString);
            }
            return wordToList;

        } catch (IOException ex) {
            throw new ValidateFileNameException("Table name " + csvTableName + " does not exist!");
        }
    }

    private int insertToDatabase(Set<WordDtoString> wordsFromCSV) {
        Set<String> allWordsFromDatabase = wordRepository.getAllWords();

        List<Word> saved = wordRepository.saveAll(wordsFromCSV.stream().map(WordDtoString::getWord)
                .filter(s -> !(allWordsFromDatabase.contains(s)))
                .map(s -> new Word().setWord(s)).collect(Collectors.toSet()));

        return saved.size();
    }

}
