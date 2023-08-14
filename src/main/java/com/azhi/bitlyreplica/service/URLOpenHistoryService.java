package com.azhi.bitlyreplica.service;

import com.azhi.bitlyreplica.dto.URLOpenHistoryRequestDto;
import com.azhi.bitlyreplica.dto.URLOpenHistoryResponseDto;

import java.util.List;

public interface URLOpenHistoryService {
    void createUrlOpenHistory(final URLOpenHistoryRequestDto urlOpenHistoryRequestDto) throws InterruptedException;

    List<URLOpenHistoryResponseDto> getUrlOpenHistory(final String shortenedUrlEncryptedKey);
}
