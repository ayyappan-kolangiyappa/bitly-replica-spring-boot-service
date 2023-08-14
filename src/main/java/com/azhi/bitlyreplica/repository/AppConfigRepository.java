package com.azhi.bitlyreplica.repository;

import com.azhi.bitlyreplica.entity.AppConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AppConfigRepository extends JpaRepository<AppConfig, Long> {
    Optional<AppConfig> findByConfigKey(String configKey);

    @Modifying
    @Query("update AppConfig ac set ac.configValue = :configValue where ac.configKey = :configKey")
    Optional<AppConfig> updateConfigValueByConfigKey(String configKey, String configValue);
}
