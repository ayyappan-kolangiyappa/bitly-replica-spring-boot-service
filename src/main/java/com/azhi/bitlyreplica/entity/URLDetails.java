package com.azhi.bitlyreplica.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "URL_DETAIL")
public class URLDetails {
    @Id
    private String shortenedUrlEncryptedKey;
    @Column(unique = true)
    private String originalUrl;
    private String shortenedUrl;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
}
