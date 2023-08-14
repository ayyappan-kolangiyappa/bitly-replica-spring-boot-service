package com.azhi.bitlyreplica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class URLShorteningRequestDto {
    private String originalUrl;
}
