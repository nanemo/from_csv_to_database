package com.nanemo.from_csv_to_database.repository;

import com.nanemo.from_csv_to_database.dto.TitleTextDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TitleTextRepository extends JpaRepository<TitleTextRepository, Integer> {

    @Query("select t.title, Text.text from Title t left join Text on Text.title=t.text")
    Set<TitleTextDto> getAllTextsAndTitlesTogether();

    @Query("select case when count (t) > 0 then true else false end from Title t left join Text on" +
            " t.text=Text.title where t.title=:titleParam and Text.text=:textParam")
    boolean findByTextAndTitleTogether(@Param("titleParam") String title,
                                       @Param("textParam") String text);

    //TODO Bunu bol iki Repository-ye Text and Title

//    select ti.title, te.text from Text te left join Title ti on te.title=ti.text where " +
//            "ti.title = : titleParam and te.text = :textParam
}
