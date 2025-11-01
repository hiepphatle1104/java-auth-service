package com.swappie.repository;

import com.swappie.domain.AccessToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessTokenRepository extends CrudRepository<AccessToken, String> {
    Optional<AccessToken> findByToken(String token);
}
