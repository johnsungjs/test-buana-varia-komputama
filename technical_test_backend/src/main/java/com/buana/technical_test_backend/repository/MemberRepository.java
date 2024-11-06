package com.buana.technical_test_backend.repository;

import com.buana.technical_test_backend.entity.Member;


public interface MemberRepository extends CustomRepository<Member, String> {
  Member findByCredential(String credential);
}
