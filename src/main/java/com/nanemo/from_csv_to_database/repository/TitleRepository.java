package com.nanemo.from_csv_to_database.repository;

import com.nanemo.from_csv_to_database.entity.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TitleRepository extends JpaRepository<Title, Integer> {

    @Query(value = "select t.* from titles t left join texts tx on tx.title_id=t.title_id where t.title=:titleParam limit :limit", nativeQuery = true)
    Optional<Title> findByTitle(@Param("titleParam") String title, @Param("limit") int limit);

}
