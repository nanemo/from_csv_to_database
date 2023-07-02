package com.nanemo.from_csv_to_database.service.impl;

import com.nanemo.from_csv_to_database.dto.TitleTextDto;
import com.nanemo.from_csv_to_database.entity.Text;
import com.nanemo.from_csv_to_database.entity.Title;
import com.nanemo.from_csv_to_database.exception.ValidateFileNameException;
import com.nanemo.from_csv_to_database.repository.TextRepository;
import com.nanemo.from_csv_to_database.repository.TitleRepository;
import com.nanemo.from_csv_to_database.service.TitleService;
import com.nanemo.from_csv_to_database.util.FilePath;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TitleServiceImpl implements TitleService {
    private static final String BAD_REQUEST_MESSAGE = "No files were inserted into the database!";
    private static final String NOT_FOUND_MESSAGE = "No files for inserting into the database!";

    @Value("${file.directory}")
    private String fileDirectory;

    private final TitleRepository titleRepository;
    private final TextRepository textRepository;
    private final FilePath filePath;

    @Override
    @Transactional
    public ResponseEntity<Object> insert(String csvTableName) {
        Set<TitleTextDto> titleTextDtoSet;

        titleTextDtoSet = new HashSet<>(readWordsFromCSV(csvTableName));

        return insertIntoDatabase(titleTextDtoSet);
    }

    private List<TitleTextDto> readWordsFromCSV(String csvTableName) {
        String fileName = filePath.tableNameGenerator(fileDirectory, csvTableName);

        try {
            return new CsvToBeanBuilder<TitleTextDto>(new FileReader(fileName))
                    .withType(TitleTextDto.class)
                    .build()
                    .parse();
        } catch (FileNotFoundException ex) {
            throw new ValidateFileNameException("Table name " + csvTableName + " does not exist!", ex);
        }

    }

    private ResponseEntity<Object> insertIntoDatabase(Set<TitleTextDto> titleTextListFromCSV) {
        Set<TitleTextDto> checkedTitleTextListFromCSV = isTitleAndTextCouple(titleTextListFromCSV);

        if (Objects.isNull(checkedTitleTextListFromCSV) || checkedTitleTextListFromCSV.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(NOT_FOUND_MESSAGE);
        }

        Set<Title> savedTitles = checkedTitleTextListFromCSV.stream().filter(titleTextDto -> {
            Optional<Title> fromDataByTitle = titleRepository.findByTitleOrderByTitleId(titleTextDto.getTitle());
            return fromDataByTitle.isEmpty() || !Objects.equals(fromDataByTitle.get().getText().getText(), titleTextDto.getText());
        }).map(titleTextDto -> {

            Title title = new Title().setTitle(titleTextDto.getTitle());
            Text text = new Text().setText(titleTextDto.getText());

            title.setText(text);
            text.setTitle(title);
            textRepository.save(text);
            return titleRepository.save(title);

        }).collect(Collectors.toSet());

        if (savedTitles.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BAD_REQUEST_MESSAGE);
        }

        return ResponseEntity.ok(savedTitles);
    }

    private Set<TitleTextDto> isTitleAndTextCouple(Set<TitleTextDto> titleTextListFromCSV) {
        return titleTextListFromCSV.stream().filter(Objects::nonNull).filter(titleTextDto ->
                Objects.nonNull(titleTextDto.getText()) &&
                        Objects.nonNull(titleTextDto.getTitle()) &&
                        !titleTextDto.getTitle().isEmpty() && !titleTextDto.getText().isEmpty()).collect(Collectors.toSet());
    }

}
