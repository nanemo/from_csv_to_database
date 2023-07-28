package com.nanemo.from_csv_to_database.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Getter
@Setter
@Component
public class WordDtoString implements Serializable {
    private String word;
    private String anotherColumn;
}
