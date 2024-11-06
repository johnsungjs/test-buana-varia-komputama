package com.buana.technical_test_backend.entity;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection="member")
@Data
public class Member {
  private String credential;
  private String name;
  private String positionId;
  private String reportTo;
  private List<String> subordinate;
}
