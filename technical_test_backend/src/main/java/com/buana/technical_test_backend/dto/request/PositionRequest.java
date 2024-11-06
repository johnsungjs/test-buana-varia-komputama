package com.buana.technical_test_backend.dto.request;

import java.util.List;

import lombok.Data;

@Data
public class PositionRequest {

    private String name;
    private List<String> authorizeTo;
}
