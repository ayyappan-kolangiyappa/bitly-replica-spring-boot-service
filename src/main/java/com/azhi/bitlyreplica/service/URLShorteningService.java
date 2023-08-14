package com.azhi.bitlyreplica.service;

import com.azhi.bitlyreplica.dto.URLShorteningRequestDto;
import com.azhi.bitlyreplica.dto.URLShorteningResponseDto;

import java.util.List;

public interface URLShorteningService {
    URLShorteningResponseDto shortenUrl(URLShorteningRequestDto urlShorteningRequestDto);
    List<URLShorteningResponseDto> getAllShortenedUrls();
}
