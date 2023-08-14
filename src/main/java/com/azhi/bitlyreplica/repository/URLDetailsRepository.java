package com.azhi.bitlyreplica.repository;

import com.azhi.bitlyreplica.entity.URLDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface URLDetailsRepository extends JpaRepository<URLDetails, String> {
    Optional<URLDetails> findByShortenedUrlEncryptedKey(final String encryptedId);
    boolean existsByShortenedUrlEncryptedKey(final String encryptedId);
    boolean existsByOriginalUrl(final String originalUrl);
}
