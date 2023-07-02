package com.nanemo.from_csv_to_database.repository;

import com.nanemo.from_csv_to_database.entity.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextRepository extends JpaRepository<Text, Integer> {
}
