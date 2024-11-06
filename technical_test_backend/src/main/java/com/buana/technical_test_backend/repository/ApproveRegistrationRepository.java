package com.buana.technical_test_backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.buana.technical_test_backend.entity.ApproveRegistration;

public interface ApproveRegistrationRepository extends MongoRepository<ApproveRegistration, String> {

    ApproveRegistration findOneById(String id);
    void deleteOneById(String id);

}
