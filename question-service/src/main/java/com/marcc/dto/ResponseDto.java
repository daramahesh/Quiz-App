package com.marcc.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResponseDto {
    private int id;
    private String response;
}
