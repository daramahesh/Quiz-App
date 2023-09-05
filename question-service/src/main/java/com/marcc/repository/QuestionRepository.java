package com.marcc.repository;

import com.marcc.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String category);
    @Query(value = "select q.id from question q where q.category=:category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
    List<Integer> findRandomByCategory(String category, int numQ);
}
