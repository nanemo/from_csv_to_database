package com.nanemo.from_csv_to_database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "words")
public class Word implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "word_id")
    private Integer wordId;
    @Column(name = "word")
    private String word;

    public Word setWord(String word) {
        this.word = word;
        return this;
    }

}

