package com.azhi.bitlyreplica.ratelimiter.enums;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class URLRedirectRateLimiterInterceptor implements HandlerInterceptor {
    private final URLRedirectRateLimiterService urlRedirectRateLimiterService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (request.getServletPath().lastIndexOf("/") != 0) {
            return true;
        }
        String remoteHostAndShortenedUrlEncryptedKey = request.getRemoteHost() + "_" + request.getContextPath().replace("/", "");

        Bucket tokenBucket = urlRedirectRateLimiterService.resolveBucket(remoteHostAndShortenedUrlEncryptedKey);
        ConsumptionProbe probe = tokenBucket.tryConsumeAndReturnRemaining(1);
        if (probe.isConsumed()) {
            return true;
        } else {
//            long waitForRefill = probe.getNanosToWaitForRefill() / 1_000_000_000;
//            response.addHeader("X-Rate-Limit-Retry-After-Seconds", String.valueOf(waitForRefill));
            response.sendError(HttpStatus.TOO_MANY_REQUESTS.value(),
                    "You have exhausted link open rate.");
            return false;
        }
    }
}
