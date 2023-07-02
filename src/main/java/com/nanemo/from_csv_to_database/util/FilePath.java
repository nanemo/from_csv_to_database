package com.nanemo.from_csv_to_database.util;

import org.springframework.stereotype.Component;

@Component
public class FilePath {

    public String tableNameGenerator(String fileDirectory, String csvTableName) {
        return String.format("%s%s.csv", fileDirectory, csvTableName);
    }

}
