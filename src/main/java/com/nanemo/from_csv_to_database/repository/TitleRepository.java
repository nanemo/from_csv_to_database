package com.nanemo.from_csv_to_database.repository;

import com.nanemo.from_csv_to_database.entity.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TitleRepository extends JpaRepository<Title, Integer> {

    @Query(value = "select t.titleId, t.title from Title t where t.title=:titleParam ORDER BY t.titleId")
    Optional<Title> findByTitleWithLimit(@Param("titleParam") String title);

    Optional<Title> findByTitleOrderByTitleId(String title);
}
