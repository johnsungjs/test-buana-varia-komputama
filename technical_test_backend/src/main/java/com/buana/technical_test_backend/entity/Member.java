package com.buana.technical_test_backend.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.buana.technical_test_backend.dto.request.MemberRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "member")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    private String id;
    private String credential;
    private String name;
    private String positionId;
    private String reportTo;
    private List<String> subordinate;

    public Member generateMemberFromMemberRequest(MemberRequest request) {
        return new Member(null, request.getCredential(), request.getName(), request.getPositionId(), request.getReportTo(), request.getSubordinate());
    }
}
