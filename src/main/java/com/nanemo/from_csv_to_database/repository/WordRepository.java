package com.nanemo.from_csv_to_database.repository;

import com.nanemo.from_csv_to_database.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface WordRepository extends JpaRepository<Word, Integer> {

    @Query("select w.word from Word w")
    Set<String> getAllWords();

}
