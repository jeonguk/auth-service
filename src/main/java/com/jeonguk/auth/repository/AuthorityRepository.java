package com.jeonguk.auth.repository;

import com.jeonguk.auth.domain.Authority;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends ReactiveMongoRepository<Authority, String> {
}