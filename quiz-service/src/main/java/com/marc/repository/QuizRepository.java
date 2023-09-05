package com.marc.repository;

import com.marc.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
}
