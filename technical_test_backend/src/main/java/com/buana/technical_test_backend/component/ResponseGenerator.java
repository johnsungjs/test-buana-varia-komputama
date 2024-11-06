package com.buana.technical_test_backend.component;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.buana.technical_test_backend.dto.response.ApiResponse;

@Component
public class ResponseGenerator {
  public ResponseEntity<ApiResponse> success(Object data, String rc, String rd) {

    return ResponseEntity
        .status(HttpStatus.OK)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .body(new ApiResponse(data, rc, rd));
  }

  public ResponseEntity<ApiResponse> created(String rc, String rd) {
    return ResponseEntity
        .status(HttpStatus.OK)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .body(new ApiResponse(null, rc, rd));
  }

  public ResponseEntity<ApiResponse> internalError(String rc, String rd) {
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .body(new ApiResponse(null, rc, rd));
  }

  public ResponseEntity<ApiResponse> badRequest(String rc, String rd) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .body(new ApiResponse(null, rc, rd));
  }
}

