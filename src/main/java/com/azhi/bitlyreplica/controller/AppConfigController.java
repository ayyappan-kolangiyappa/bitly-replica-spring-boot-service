package com.azhi.bitlyreplica.controller;

import com.azhi.bitlyreplica.dto.AppConfigRequestDto;
import com.azhi.bitlyreplica.service.AppConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/app-config")
public class AppConfigController {
    private final AppConfigService appConfigService;

    @PostMapping
    public ResponseEntity<?> updateAppConfig(@RequestBody final AppConfigRequestDto appConfigRequestDto) {
        appConfigService.updateAppConfig(appConfigRequestDto);
        return ResponseEntity.status(200).build();
    }

}
