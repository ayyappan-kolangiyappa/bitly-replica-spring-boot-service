package com.azhi.bitlyreplica.service;

import com.azhi.bitlyreplica.dto.URLOpenHistoryRequestDto;
import com.azhi.bitlyreplica.entity.URLDetails;
import com.azhi.bitlyreplica.repository.URLDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class URLRedirectServiceImpl implements URLRedirectService {
    private final URLDetailsRepository urlDetailsRepository;
    private final URLOpenHistoryService urlOpenHistoryService;

    @Override
    public String getOriginalUrl(String shortenedUrlEncryptedKey, String openedFromHost) {
        URLDetails urlDetails = urlDetailsRepository.findByShortenedUrlEncryptedKey(shortenedUrlEncryptedKey)
                .orElseThrow(() -> new RuntimeException("Invalid url passed."));
        URLOpenHistoryRequestDto urlOpenHistoryRequestDto = new URLOpenHistoryRequestDto(shortenedUrlEncryptedKey, openedFromHost);
        try {
            urlOpenHistoryService.createUrlOpenHistory(urlOpenHistoryRequestDto);
        } catch (InterruptedException ignored) {
        }
//        urlOpenHistoryService.createUrlOpenHistory(urlOpenHistoryRequestDto);
        return urlDetails.getOriginalUrl();
    }

}
