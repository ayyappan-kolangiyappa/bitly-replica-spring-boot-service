package com.azhi.bitlyreplica.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class URLOpenHistoryResponseDto {
    private String shortenedUrlEncryptedKey;
    private String openedFromHost;
    private LocalDateTime openedAt;
}
