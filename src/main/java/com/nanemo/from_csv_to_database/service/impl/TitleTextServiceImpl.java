package com.nanemo.from_csv_to_database.service.impl;

import com.nanemo.from_csv_to_database.dto.TitleTextDto;
import com.nanemo.from_csv_to_database.dto.WordDtoString;
import com.nanemo.from_csv_to_database.entity.Word;
import com.nanemo.from_csv_to_database.repository.TitleTextRepository;
import com.nanemo.from_csv_to_database.service.TitleTextService;
import com.nanemo.from_csv_to_database.util.FilePath;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TitleTextServiceImpl implements TitleTextService {

    @Value("${file.directory}")
    private String fileDirectory;

    private final TitleTextRepository titleTextRepository;
    private final FilePath filePath;


    @Override
    public ResponseEntity<String> insert(String csvTableName) {
        titleTextRepository.
        return null;
    }

    private List<WordDtoString> readWordsFromCSV(String csvTableName) throws FileNotFoundException {
        String fileName = filePath.tableNameGenerator(fileDirectory, csvTableName);
        return new CsvToBeanBuilder<WordDtoString>(new FileReader(fileName))
                .withType(WordDtoString.class)
                .build()
                .parse();
    }

    private int insertToDatabase(Set<TitleTextDto> titleTextFromCSV) {
        Set<String> allWordsFromDatabase = repository.getAllWords();

        List<Word> saved = repository.saveAll(titleTextFromCSV.stream().map(WordDtoString::getWord)
                .filter(s -> !(allWordsFromDatabase.contains(s)))
                .map(s -> new Word().setWord(s)).collect(Collectors.toSet()));

        return saved.size();
    }

}
