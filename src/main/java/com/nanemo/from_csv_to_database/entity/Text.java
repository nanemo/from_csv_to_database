package com.nanemo.from_csv_to_database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "texts")
public class Text {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "text_id")
    private Integer textId;

    @Column(name = "text")
    private String text;

    @OneToOne
    @JoinColumn(name = "title_id")
    private Title title;

    public Title getTitle() {
        if (this.title == null) {
            this.title = new Title();
        }
        return this.title;
    }

    public Text setText(String text) {
        this.text = text;
        return this;
    }

}
