package com.marc.dto;

import lombok.Data;

@Data
public class QuizDto {
    private String category;
    private Integer numOfQuestions;
    private String title;
}
