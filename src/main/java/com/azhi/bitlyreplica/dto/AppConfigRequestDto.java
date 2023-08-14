package com.azhi.bitlyreplica.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppConfigRequestDto {
    private String configKey;
    private String configValue;
}
