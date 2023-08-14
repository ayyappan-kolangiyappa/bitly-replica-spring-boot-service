package com.azhi.bitlyreplica.repository;

import com.azhi.bitlyreplica.entity.URLOpenHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface URLOpenHistoryRepository extends JpaRepository<URLOpenHistory, Long> {
    List<URLOpenHistory> findAllByShortenedUrlEncryptedKey(String shortenedUrlEncryptedKey);
}
