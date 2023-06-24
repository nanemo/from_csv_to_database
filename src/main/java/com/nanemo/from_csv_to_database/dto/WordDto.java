package com.nanemo.from_csv_to_database.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WordDto {
    private String tableName;
    private Integer rowId;
    private Set<String> values;
}
