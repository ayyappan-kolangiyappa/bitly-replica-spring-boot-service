package com.azhi.bitlyreplica.ratelimiter.enums;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class URLRedirectRateLimiterInterceptorConfig implements WebMvcConfigurer {
    private final URLRedirectRateLimiterInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .excludePathPatterns("/api/**");
    }
}
