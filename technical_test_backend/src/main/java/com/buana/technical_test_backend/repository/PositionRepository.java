package com.buana.technical_test_backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.buana.technical_test_backend.entity.Position;

public interface PositionRepository extends MongoRepository<Position, String> {

    Position findOneById(String id);

    void deleteOneById(String id);

}
