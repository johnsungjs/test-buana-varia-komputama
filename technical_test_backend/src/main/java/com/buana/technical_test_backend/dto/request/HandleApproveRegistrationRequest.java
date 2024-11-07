package com.buana.technical_test_backend.dto.request;

import lombok.Data;

@Data
public class HandleApproveRegistrationRequest {

    private String id;
    private String notes = "";

}
