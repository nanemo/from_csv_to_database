package com.nanemo.from_csv_to_database.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class WordDtoString implements Serializable {
    private String word;
}
