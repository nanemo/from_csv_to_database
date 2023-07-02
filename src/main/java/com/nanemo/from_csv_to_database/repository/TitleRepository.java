package com.nanemo.from_csv_to_database.repository;

import com.nanemo.from_csv_to_database.entity.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TitleRepository extends JpaRepository<Title, Integer> {
    Optional<Title> findByTitleOrderByTitleId(String title);

}
