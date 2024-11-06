package com.buana.technical_test_backend.repository;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomRepository<T, ID extends Serializable> extends MongoRepository<T, ID> {

    public T findOne(ID id);

    public void delete(ID id);
}
