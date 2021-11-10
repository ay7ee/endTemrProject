package com.example.project.repositories;

import com.example.project.models.Answers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface
AnswersRepository extends JpaRepository<Answers, Long> {
    @Query(value = "SELECT * FROM answers WHERE question_id = :id", nativeQuery = true)
    List<Answers> findAnswersByAquestion_id(@Param("id") Long id);
}
