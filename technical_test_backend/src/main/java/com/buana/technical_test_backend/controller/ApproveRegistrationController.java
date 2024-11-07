package com.buana.technical_test_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buana.technical_test_backend.component.ResponseGenerator;
import com.buana.technical_test_backend.dto.request.ApproveRegistrationRequest;
import com.buana.technical_test_backend.dto.request.HandleApproveRegistrationRequest;
import com.buana.technical_test_backend.dto.response.ApiResponse;
import com.buana.technical_test_backend.entity.ApproveRegistration;
import com.buana.technical_test_backend.repository.ApproveRegistrationRepository;
import com.buana.technical_test_backend.service.ApproveRegistrationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/approve-registration")
@Slf4j
public class ApproveRegistrationController {

    @Autowired
    private ResponseGenerator rg;

    @Autowired
    private ApproveRegistrationRepository repository;

    @Autowired
    private ApproveRegistrationService service;

    @GetMapping("/find-all")
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

    @GetMapping("/find-one")
    public ResponseEntity<ApiResponse> findOne(@RequestParam String id) {
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

    @PostMapping("/add-one")
    public ResponseEntity<ApiResponse> addOne(@RequestBody ApproveRegistrationRequest request) {
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

    @PatchMapping("/update-one")
    public ResponseEntity<ApiResponse> updateOne(@RequestBody ApproveRegistration request) {
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

    @DeleteMapping("/delete-by-id")
    public ResponseEntity<ApiResponse> deleteById(@RequestParam String id) {
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

    @PostMapping("/create-registration-member")
    public ResponseEntity<ApiResponse> makeRegisterMember(@RequestBody ApproveRegistrationRequest request) {
        return service.addOne(request);
    }

    @PostMapping("approve-register-member")
    public ResponseEntity<ApiResponse> approveRegisterMember(@RequestBody HandleApproveRegistrationRequest request) {
        return service.accept(request);
    }

    @PostMapping("reject-register-member")
    public ResponseEntity<ApiResponse> rejectRegisterMember(@RequestBody HandleApproveRegistrationRequest request) {
        return service.reject(request);
    }

    @PostMapping("reset-status-register-member")
    public ResponseEntity<ApiResponse> resetStatusRegisterMember(@RequestBody HandleApproveRegistrationRequest request) {
        return service.resetStatus(request);
    }

    @GetMapping("/find-all-unhandled")
    public ResponseEntity<ApiResponse> findAllUnhandled() {
        try {
            log.info("Find All unhandled registration");
            List<ApproveRegistration> data = repository.findByApprovalStatus(0);
            return rg.success(data, "00", "Success Get All Approved Registration");
        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
            return rg.internalError("99", e.getMessage());
        }
    }

    @GetMapping("/find-all-approved")
    public ResponseEntity<ApiResponse> findAllApproved() {
        try {
            log.info("Find All approve registration");
            List<ApproveRegistration> data = repository.findByApprovalStatus(1);
            return rg.success(data, "00", "Success Get All Approved Registration");
        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
            return rg.internalError("99", e.getMessage());
        }
    }

    @GetMapping("/find-all-rejected")
    public ResponseEntity<ApiResponse> findAllRejected() {
        try {
            log.info("Find All approve registration");
            List<ApproveRegistration> data = repository.findByApprovalStatus(2);
            return rg.success(data, "00", "Success Get All Rejected Registration");
        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
            return rg.internalError("99", e.getMessage());
        }
    }

}
