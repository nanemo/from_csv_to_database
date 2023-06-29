package com.nanemo.from_csv_to_database.repository;

import com.nanemo.from_csv_to_database.entity.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TitleRepository extends JpaRepository<Title, Integer> {

    @Query("select t from Title t left join Text tx on tx.title=t.text where t.title=:titleParam")
    Optional<Title> findByTitle(@Param("titleParam") String title, @Param("limit") int limit);

}
