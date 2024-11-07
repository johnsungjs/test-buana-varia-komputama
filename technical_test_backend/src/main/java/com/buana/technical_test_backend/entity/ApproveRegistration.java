package com.buana.technical_test_backend.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.buana.technical_test_backend.dto.request.ApproveRegistrationRequest;
import com.buana.technical_test_backend.dto.request.MemberRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "approveRegistration")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApproveRegistration {

    // APPROVAL STATUS
    //0 -> Unhandled 
    //1 -> Approved 
    //2 -> Rejected
    
    @Id
    private String id;
    private MemberRequest dataMember;
    private int approvalStatus;
    private String notes = "";

    public ApproveRegistration generateApproveRegistrationFromRequest(ApproveRegistrationRequest request) {
        return new ApproveRegistration(null, request.getDataMember(), 0, request.getNotes());
    }
}
