package com.nanemo.from_csv_to_database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "titles")
public class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "title_id")
    private Integer titleId;

    @Column(name = "title")
    private String title;

    @OneToOne(mappedBy = "title")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JsonIgnore
    private Text text;

    public Text getText() {
        if (this.text == null) {
            this.text = new Text();
        }
        return this.text;
    }

    public Title setTitle(String title) {
        this.title = title;
        return this;
    }

}
