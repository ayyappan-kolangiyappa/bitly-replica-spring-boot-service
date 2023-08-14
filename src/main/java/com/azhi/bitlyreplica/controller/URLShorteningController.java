package com.azhi.bitlyreplica.controller;

import com.azhi.bitlyreplica.dto.URLShorteningRequestDto;
import com.azhi.bitlyreplica.dto.URLShorteningResponseDto;
import com.azhi.bitlyreplica.service.URLShorteningService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/url/shortening")
@RestController
public class URLShorteningController {
    private final URLShorteningService urlShorteningService;

    @PostMapping
    public URLShorteningResponseDto shortenedUrl(@RequestBody final URLShorteningRequestDto requestDto) {
        return urlShorteningService.shortenUrl(requestDto);
    }

    @GetMapping
    public List<URLShorteningResponseDto> getAllShortenedUrls() {
        return urlShorteningService.getAllShortenedUrls();
    }
}
