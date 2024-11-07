package com.buana.technical_test_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.buana.technical_test_backend.component.ResponseGenerator;
import com.buana.technical_test_backend.dto.request.ApproveRegistrationRequest;
import com.buana.technical_test_backend.dto.request.HandleApproveRegistrationRequest;
import com.buana.technical_test_backend.dto.response.ApiResponse;
import com.buana.technical_test_backend.entity.ApproveRegistration;
import com.buana.technical_test_backend.entity.Member;
import com.buana.technical_test_backend.repository.ApproveRegistrationRepository;
import com.buana.technical_test_backend.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApproveRegistrationService {

    @Autowired
    private ResponseGenerator rg;

    @Autowired
    private ApproveRegistrationRepository repository;

    @Autowired
    private MemberRepository memberRepository;

    public ResponseEntity<ApiResponse> findAll() {
        try {
            log.info("Find All approve registration");
            List<ApproveRegistration> data = repository.findAll();
            return rg.success(data, "00", "Success Get All Registration Member Data");
        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
            return rg.internalError("99", e.getMessage());
        }
    }

    public ResponseEntity<ApiResponse> findOne(String id) {
        try {
            log.info("Find One Member");
            ApproveRegistration data = repository.findOneById(id);
            if (data == null) {
                String respMessage = "No Approve Member With ID " + id;
                return rg.success(null, "00", respMessage);
            }
            return rg.success(data, "00", "Success Get One Member By id");
        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
            return rg.internalError("99", e.getMessage());
        }
    }

    public ResponseEntity<ApiResponse> addOne(ApproveRegistrationRequest request) {
        try {
            log.info("Add One Approve Registration");
            log.info("debug request: " + request.toString());

            ApproveRegistration dataToSave = new ApproveRegistration().generateApproveRegistrationFromRequest(request);

            repository.save(dataToSave);
            return rg.success(null, "00", "Success Add One Request Approve Registration");
        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
            return rg.internalError("99", e.getMessage());
        }
    }

    public ResponseEntity<ApiResponse> updateOne(ApproveRegistration request) {
        try {
            log.info("Update One Approve Registration");
            ApproveRegistration existing = repository.findOneById(request.getId());
            if (existing == null) {
                String respMessage = "No Approve Registration With ID " + request.getId();
                return rg.success(null, "00", respMessage);
            }

            repository.save(request);
            return rg.success(null, "00", "Success Update One Member");
        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
            return rg.internalError("99", e.getMessage());
        }
    }

    public ResponseEntity<ApiResponse> deleteById(String id) {
        try {
            log.info("Delete One Approve Registration");
            ApproveRegistration data = repository.findOneById(id);
            if (data == null) {
                String respMessage = "No Approve Registration With ID " + id;
                return rg.badRequest("00", respMessage);
            }
            repository.deleteOneById(id);
            return rg.success(null, "00", "Success Delete One Approve Registration By ID");
        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
            return rg.internalError("99", e.getMessage());
        }
    }

    public ResponseEntity<ApiResponse> accept(HandleApproveRegistrationRequest request) {
        try {
            ApproveRegistration existing = repository.findOneById(request.getId());

            if (existing == null) {
                return rg.internalError("01", "No Approve Request With ID " + request.getId());
            }

            existing.setApprovalStatus(1);
            existing.setNotes(request.getNotes());
            repository.save(existing);

            Member memberToSave = new Member().generateMemberFromMemberRequest(existing.getDataMember());
            memberRepository.save(memberToSave);

            return rg.success(null, "00", "Success Approve One Member Registration ");

        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
            return rg.internalError("99", e.getMessage());
        }
    }

    public ResponseEntity<ApiResponse> reject(HandleApproveRegistrationRequest request) {
        try {
            ApproveRegistration existing = repository.findOneById(request.getId());

            if (existing == null) {
                return rg.internalError("01", "No Approve Request With ID " + request.getId());
            }

            existing.setApprovalStatus(2);
            existing.setNotes(request.getNotes());
            repository.save(existing);

            return rg.success(null, "00", "Success Approve One Member Registration ");

        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
            return rg.internalError("99", e.getMessage());
        }
    }

    public ResponseEntity<ApiResponse> resetStatus(HandleApproveRegistrationRequest request) {
        try {
            ApproveRegistration existing = repository.findOneById(request.getId());

            if (existing == null) {
                return rg.internalError("01", "No Approve Request With ID " + request.getId());
            }

            existing.setApprovalStatus(0);
            existing.setNotes(request.getNotes());
            repository.save(existing);

            return rg.success(null, "00", "Success Approve One Member Registration ");

        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
            return rg.internalError("99", e.getMessage());
        }
    }
}
