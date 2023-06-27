package com.nanemo.from_csv_to_database.util;

import com.nanemo.from_csv_to_database.dto.WordDtoString;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Component
public class FilePath {

    public String tableNameGenerator(String fileDirectory, String csvTableName) {
        return String.format("%s%s.csv", fileDirectory, csvTableName);
    }

}
