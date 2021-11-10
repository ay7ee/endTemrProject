package com.example.project.models;


import com.example.project.repositories.UsersRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "questions")
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long question_id;

    private String title;
    private String question;
    private long author_id;

    public long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(long question_id) {
        this.question_id = question_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(long author_id) {
        this.author_id = author_id;
    }

    public Questions(){};
    public Questions(String title, String question, long author_id) {
        this.title = title;
        this.question = question;
        this.author_id = author_id;
    }
}
