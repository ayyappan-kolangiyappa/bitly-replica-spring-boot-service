package com.azhi.bitlyreplica.service;

import com.azhi.bitlyreplica.dto.URLOpenHistoryRequestDto;
import com.azhi.bitlyreplica.dto.URLOpenHistoryResponseDto;
import com.azhi.bitlyreplica.entity.URLOpenHistory;
import com.azhi.bitlyreplica.repository.URLOpenHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class URLOpenHistoryServiceImpl implements URLOpenHistoryService {
    private final URLOpenHistoryRepository urlOpenHistoryRepository;

    @Async
    @Retryable(retryFor = SQLException.class, maxAttempts = 2, backoff = @Backoff(delay = 100))
    @Override
    public void createUrlOpenHistory(URLOpenHistoryRequestDto urlOpenHistoryRequestDto) {
        URLOpenHistory urlOpenHistory = URLOpenHistory.builder()
                .shortenedUrlEncryptedKey(urlOpenHistoryRequestDto.getShortenedUrlEncryptedKey())
                .openedFromHost(urlOpenHistoryRequestDto.getOpenedFromHost())
                .openedAt(LocalDateTime.now())
                .build();
        urlOpenHistoryRepository.save(urlOpenHistory);
        log.info("Saved URLOpenHistory on DB. urlOpenHistory: {}", urlOpenHistory);
    }

    @Override
    public List<URLOpenHistoryResponseDto> getUrlOpenHistory(String shortenedUrlEncryptedKey) {
        List<URLOpenHistory> urlOpenHistoryList = urlOpenHistoryRepository.findAllByShortenedUrlEncryptedKey(shortenedUrlEncryptedKey);
        return urlOpenHistoryList.stream()
                .map(urlOpenHistory -> URLOpenHistoryResponseDto.builder()
                        .shortenedUrlEncryptedKey(urlOpenHistory.getShortenedUrlEncryptedKey())
                        .openedFromHost(urlOpenHistory.getOpenedFromHost())
                        .openedAt(urlOpenHistory.getOpenedAt())
                        .build())
                .toList();
    }

}
