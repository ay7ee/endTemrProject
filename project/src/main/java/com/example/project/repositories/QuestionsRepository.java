package com.example.project.repositories;

import com.example.project.models.Questions;
import org.springframework.data.repository.CrudRepository;

public interface QuestionsRepository extends CrudRepository<Questions, Long> {

}
