package com.nanemo.from_csv_to_database.repository;

import com.nanemo.from_csv_to_database.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<Word, Integer> {
    Optional<Word> findByWord(String word);
}
