package com.buana.technical_test_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDetailResponse {

    private String credential;
    private String name;
    private String positionName;
    private String reportsTo;
}
