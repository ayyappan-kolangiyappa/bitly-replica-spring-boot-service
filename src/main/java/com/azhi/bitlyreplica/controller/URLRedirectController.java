package com.azhi.bitlyreplica.controller;

import com.azhi.bitlyreplica.ratelimiter.enums.URLRedirectRateLimiterService;
import com.azhi.bitlyreplica.service.URLRedirectService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.Duration;

@Slf4j
@RequiredArgsConstructor
@RestController
public class URLRedirectController {
    private final URLRedirectService urlRedirectService;
    private final URLRedirectRateLimiterService urlRedirectRateLimiterService;

    @GetMapping("/{encryptedId}")
    public ResponseEntity<Void> redirectToOriginalUrl(HttpServletRequest httpReq, @PathVariable String encryptedId) {

        if (encryptedId.trim().isEmpty() || encryptedId.contains("/")) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .build();
        }

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(urlRedirectService.getOriginalUrl(encryptedId, httpReq.getRemoteHost())))
                .build();
    }

}
