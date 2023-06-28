package com.nanemo.from_csv_to_database.repository;

import com.nanemo.from_csv_to_database.dto.TitleTextDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TitleRepository extends JpaRepository<TitleRepository, Integer> {

}
