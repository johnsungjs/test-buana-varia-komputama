package com.buana.technical_test_backend.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "member")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApproveRegistration {

    @Id
    private String id;
    private Member dataMember;
    private Boolean isApprove;
    private String notes = "";
}
