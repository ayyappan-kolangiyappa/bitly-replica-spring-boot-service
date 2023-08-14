package com.azhi.bitlyreplica.service;

import com.azhi.bitlyreplica.dto.AppConfigRequestDto;
import com.azhi.bitlyreplica.entity.AppConfig;
import com.azhi.bitlyreplica.repository.AppConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AppConfigService {
    private final AppConfigRepository appConfigRepository;

    @CacheEvict(value = "appConfigs", key = "#appConfigRequestDto.configKey")
    public void updateAppConfig(final AppConfigRequestDto appConfigRequestDto) {
        AppConfig appConfig = appConfigRepository.findByConfigKey(appConfigRequestDto.getConfigKey())
                .orElseThrow(() -> new RuntimeException("App config not found."));

        appConfig.setConfigValue(appConfigRequestDto.getConfigValue());

        appConfigRepository.save(appConfig);
    }

    @Cacheable("appConfigs")
    public String getConfigValue(String configKey) {
        AppConfig appConfig = appConfigRepository.findByConfigKey(configKey)
                .orElseThrow(() -> new RuntimeException("Config not found in DB."));
        return appConfig.getConfigValue();
    }
}
