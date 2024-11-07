package com.buana.technical_test_backend.dto.request;

import java.util.List;

import lombok.Data;

@Data
public class MemberRequest {
  private String credential;
  private String name;
  private String positionId;
  private String reportsTo;
  private List<String> subordinate;
}
