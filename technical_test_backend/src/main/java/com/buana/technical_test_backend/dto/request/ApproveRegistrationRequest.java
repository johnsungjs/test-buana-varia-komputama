package com.buana.technical_test_backend.dto.request;

import lombok.Data;

@Data
public class ApproveRegistrationRequest {

    private MemberRequest dataMember;
    private Boolean isApprove;
    private String notes = "";

}
