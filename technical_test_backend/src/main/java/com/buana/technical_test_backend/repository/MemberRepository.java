package com.buana.technical_test_backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.buana.technical_test_backend.entity.Member;


public interface MemberRepository extends MongoRepository<Member, String> {
  Member findOneById(String id);
  Member findOneByCredential(String credential);
  void deleteOneByCredential(String credential);
  void deleteOneById(String id);
}
