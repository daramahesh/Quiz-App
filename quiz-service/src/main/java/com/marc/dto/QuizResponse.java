package com.marc.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class QuizResponse {
    private int id;
    private String response;
}
