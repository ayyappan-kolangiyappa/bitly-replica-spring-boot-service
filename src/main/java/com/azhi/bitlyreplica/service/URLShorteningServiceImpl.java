package com.azhi.bitlyreplica.service;

import com.azhi.bitlyreplica.constants.AppConfigKeysConstants;
import com.azhi.bitlyreplica.dto.AppConfigRequestDto;
import com.azhi.bitlyreplica.dto.URLShorteningRequestDto;
import com.azhi.bitlyreplica.dto.URLShorteningResponseDto;
import com.azhi.bitlyreplica.entity.URLDetails;
import com.azhi.bitlyreplica.repository.URLDetailsRepository;
import com.azhi.bitlyreplica.util.RandomStringGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class URLShorteningServiceImpl implements URLShorteningService {
    private final URLDetailsRepository urlDetailsRepository;
    private final RandomStringGenerator randomStringGenerator;
    @Value("${bitly-replica.base.shortened.url}")
    private String bitlyReplicaBaseShortenedUrl;
    private final AppConfigService appConfigService;

    @Override
    public URLShorteningResponseDto shortenUrl(URLShorteningRequestDto urlShorteningRequestDto) {
        validateOriginalUrl(urlShorteningRequestDto.getOriginalUrl());
        String configValue = appConfigService.getConfigValue(AppConfigKeysConstants.ENCRYPTED_ID_LENGTH_CONFIG);
        int encryptedIdLength = Integer.parseInt(configValue);
        String encryptedId = getEncryptedId(encryptedIdLength, 0);
        URLDetails urlDetails = URLDetails.builder()
                .shortenedUrlEncryptedKey(encryptedId)
                .originalUrl(urlShorteningRequestDto.getOriginalUrl())
                .shortenedUrl(bitlyReplicaBaseShortenedUrl + encryptedId)
                .createdAt(LocalDateTime.now())
                .build();
        urlDetailsRepository.save(urlDetails);
        return URLShorteningResponseDto.builder()
                .originalUrl(urlDetails.getOriginalUrl())
                .shortenedUrl(urlDetails.getShortenedUrl())
                .createdAt(urlDetails.getCreatedAt())
                .build();
    }

    private void validateOriginalUrl(String originalUrl) {
        boolean isAlreadyExists = urlDetailsRepository.existsByOriginalUrl(originalUrl);
        if (isAlreadyExists) {
            throw new RuntimeException("Original URL is already shortened.");
        }
    }

    @Override
    public List<URLShorteningResponseDto> getAllShortenedUrls() {
        List<URLDetails> urlDetailsList = urlDetailsRepository.findAll();
        return urlDetailsList.stream()
                .map(urlDetails -> URLShorteningResponseDto.builder()
                        .originalUrl(urlDetails.getOriginalUrl())
                        .shortenedUrl(urlDetails.getShortenedUrl())
                        .createdAt(urlDetails.getCreatedAt())
                        .build())
                .toList();
    }

    private String getEncryptedId(long streamSize, int retryCount) {
        String encryptedId = randomStringGenerator.randomStringGenerator(streamSize);
        boolean isExists = urlDetailsRepository.existsByShortenedUrlEncryptedKey(encryptedId);
        if (isExists) {
            if (retryCount > 10) {
                getEncryptedId(streamSize + 1, 0);
                AppConfigRequestDto requestDto = new AppConfigRequestDto(AppConfigKeysConstants.ENCRYPTED_ID_LENGTH_CONFIG, String.valueOf(streamSize + 1));
                appConfigService.updateAppConfig(requestDto);
            } else {
                getEncryptedId(streamSize, retryCount + 1);
            }
        }
        return encryptedId;
    }

}
