package com.buana.technical_test_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buana.technical_test_backend.component.ResponseGenerator;
import com.buana.technical_test_backend.dto.response.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api")
@Slf4j
public class TestController {

    @Autowired
    private ResponseGenerator rg;


    @GetMapping("/test")
    public ResponseEntity<ApiResponse> getTest() {
        return rg.success(null, "00", "SUKSES TEST");
    }
}
