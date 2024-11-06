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
import com.buana.technical_test_backend.dto.request.PositionRequest;
import com.buana.technical_test_backend.dto.response.ApiResponse;
import com.buana.technical_test_backend.entity.Position;
import com.buana.technical_test_backend.repository.PositionRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/position")
@Slf4j
public class PositionController {
    @Autowired
    private ResponseGenerator rg;

    @Autowired
    private PositionRepository repository;

    @GetMapping("/find-all")
    public ResponseEntity<ApiResponse> findAll() {
        try {
            log.info("Find All Position");
            List<Position> data = repository.findAll();
            return rg.success(data, "00", "Success Get All Position Data");
        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
            return rg.internalError("99", e.getMessage());
        }
    }

    @GetMapping("/find-one")
    public ResponseEntity<ApiResponse> findOne(@RequestParam String id) {
        try {
            log.info("Find One Position");
            Position data = repository.findOneById(id);
            if (data == null) {
                String respMessage = "Tidak Ada Position Dengan id " + id;
                return rg.success(null, "00", respMessage);
            }
            return rg.success(data, "00", "Success Get One Position By id");
        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
            return rg.internalError("99", e.getMessage());
        }
    }

    @PostMapping("/add-one")
    public ResponseEntity<ApiResponse> addOne(@RequestBody PositionRequest request) {
        try {
            log.info("Add One Position");
            log.info("debug request: " + request.toString());

            Position dataToSave = new Position().generatePositionFromRequest(request);
            repository.save(dataToSave);
            return rg.success(null, "00", "Success Add One Position");
        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
            return rg.internalError("99", e.getMessage());
        }
    }

    @PatchMapping("/update-one")
    public ResponseEntity<ApiResponse> updateOne(@RequestBody Position request) {
        try {
            log.info("Update One Position");
            Position existing = repository.findOneById(request.getId());
            if (existing == null) {
                String respMessage = "Tidak Ada Position Dengan id " + request.getId();
                return rg.success(null, "00", respMessage);
            }

            repository.save(request);
            return rg.success(null, "00", "Success Update One Position");
        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
            return rg.internalError("99", e.getMessage());
        }
    }

    @DeleteMapping("/delete-by-id")
    public ResponseEntity<ApiResponse> deleteById(@RequestParam String id) {
        try {
            log.info("Delete One Position");
            Position data = repository.findOneById(id);
            if (data == null) {
                String respMessage = "No Position Dengan Id " + id;
                return rg.badRequest("00", respMessage);
            }
            repository.deleteOneById(id);
            return rg.success(null, "00", "Success Delete One Position By ID");
        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
            return rg.internalError("99", e.getMessage());
        }
    }
}
