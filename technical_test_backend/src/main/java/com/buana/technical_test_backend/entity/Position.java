package com.buana.technical_test_backend.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.buana.technical_test_backend.dto.request.PositionRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "position")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Position {

    @Id
    private String id;
    private String name;
    private List<String> authorizeTo = new ArrayList<>();

    public Position generatePositionFromRequest(PositionRequest request) {
        return new Position(null, request.getName(), request.getAuthorizeTo());
    }
}
