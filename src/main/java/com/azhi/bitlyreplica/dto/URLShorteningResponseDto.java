package com.azhi.bitlyreplica.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class URLShorteningResponseDto {
    private String originalUrl;
    private String shortenedUrl;
    private LocalDateTime createdAt;
}
