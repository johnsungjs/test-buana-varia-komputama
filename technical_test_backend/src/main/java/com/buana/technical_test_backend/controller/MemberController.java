package com.buana.technical_test_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buana.technical_test_backend.component.ResponseGenerator;
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
            log.info("Getting All Member");
            List<Member> data = repository.findAll();
            return rg.success(data, "00", "Success Get All Member Data");
        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
            return rg.internalError("99", e.getMessage());
        }
    }
}
