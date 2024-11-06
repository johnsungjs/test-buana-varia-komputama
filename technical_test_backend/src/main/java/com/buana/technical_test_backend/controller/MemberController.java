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
import com.buana.technical_test_backend.dto.request.MemberRequest;
import com.buana.technical_test_backend.dto.response.ApiResponse;
import com.buana.technical_test_backend.entity.Member;
import com.buana.technical_test_backend.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/member")
@Slf4j
public class MemberController {

    @Autowired
    private ResponseGenerator rg;

    @Autowired
    private MemberRepository repository;

    @GetMapping("/find-all")
    public ResponseEntity<ApiResponse> findAll() {
        try {
            log.info("Find All Member");
            List<Member> data = repository.findAll();
            return rg.success(data, "00", "Success Get All Member Data");
        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
            return rg.internalError("99", e.getMessage());
        }
    }

    @GetMapping("/find-one")
    public ResponseEntity<ApiResponse> findOne(@RequestParam String credential) {
        try {
            log.info("Find One Member");
            Member data = repository.findOneByCredential(credential);
            if (data == null) {
                String respMessage = "Tidak Ada Member Dengan Credential " + credential;
                return rg.success(null, "00", respMessage);
            }
            return rg.success(data, "00", "Success Get One Member By Credential");
        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
            return rg.internalError("99", e.getMessage());
        }
    }

    @PostMapping("/add-one")
    public ResponseEntity<ApiResponse> addOne(@RequestBody MemberRequest request) {
        try {
            log.info("Add One Member");
            log.info("debug request: " + request.toString());
            Member existing = repository.findOneByCredential(request.getCredential());
            if (existing != null) {
                String respMessage = "Sudah Ada Member Dengan Credential " + request.getCredential();
                return rg.badRequest("00", respMessage);
            }
            Member dataToSave = new Member().generateMemberFromMemberRequest(request);
            repository.save(dataToSave);
            return rg.success(null, "00", "Success Add One Member");
        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
            return rg.internalError("99", e.getMessage());
        }
    }

    @PatchMapping("/update-one")
    public ResponseEntity<ApiResponse> updateOne(@RequestBody MemberRequest request) {
        try {
            log.info("Update One Member");
            Member existing = repository.findOneByCredential(request.getCredential());
            if (existing == null) {
                String respMessage = "Tidak Ada Member Dengan Credential " + request.getCredential();
                return rg.success(null, "00", respMessage);
            }

            existing.setCredential(request.getCredential());
            existing.setName(request.getName());
            existing.setPositionId(request.getPositionId());
            existing.setReportTo(request.getReportTo());
            existing.setSubordinate(request.getSubordinate());

            repository.save(existing);
            return rg.success(null, "00", "Success Update One Member");
        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
            return rg.internalError("99", e.getMessage());
        }
    }

    @DeleteMapping("/delete-by-credential")
    public ResponseEntity<ApiResponse> deleteOne(@RequestParam String credential) {
        try {
            log.info("Delete One Member");
            Member data = repository.findOneByCredential(credential);
            if (data == null) {
                String respMessage = "Tidak Ada Member Dengan Credential " + credential;
                return rg.badRequest("00", respMessage);
            }
            repository.deleteOneByCredential(credential);
            return rg.success(null, "00", "Success Delete One Member By Credential");
        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
            return rg.internalError("99", e.getMessage());
        }
    }

    @DeleteMapping("/delete-by-id")
    public ResponseEntity<ApiResponse> deleteById(@RequestParam String id) {
        try {
            log.info("Delete One Member");
            Member data = repository.findOneById(id);
            if (data == null) {
                String respMessage = "Tidak Ada Member Dengan Credential " + id;
                return rg.badRequest("00", respMessage);
            }
            repository.deleteOneById(id);
            return rg.success(null, "00", "Success Delete One Member By ID");
        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
            return rg.internalError("99", e.getMessage());
        }
    }

}
