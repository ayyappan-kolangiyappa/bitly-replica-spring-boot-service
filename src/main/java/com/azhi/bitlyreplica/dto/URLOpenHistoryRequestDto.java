package com.azhi.bitlyreplica.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class URLOpenHistoryRequestDto {
    private String shortenedUrlEncryptedKey;
    private String openedFromHost;
}
