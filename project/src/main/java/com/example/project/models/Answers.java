package com.example.project.models;


import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "answers")
public class Answers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long answer_id;

    private String answer;
    @Column(name = "question_id")
    private long aquestion_id;
    private long avtor_id;

    public long getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(long answer_id) {
        this.answer_id = answer_id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public long getAquestion_id() {
        return aquestion_id;
    }

    public void setAquestion_id(long aquestion_id) {
        this.aquestion_id = aquestion_id;
    }

    public long getAvtor_id() {
        return avtor_id;
    }

    public void setAvtor_id(long avtor_id) {
        this.avtor_id = avtor_id;
    }

    public Answers(String answer, long aquestion_id, long avtor_id) {
        this.answer = answer;
        this.aquestion_id = aquestion_id;
        this.avtor_id = avtor_id;
    }

    public Answers(){
    }
}
