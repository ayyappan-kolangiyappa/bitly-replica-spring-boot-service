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
@Table(name = "URL_OPEN_HISTORY")
public class URLOpenHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String shortenedUrlEncryptedKey;
    private String openedFromHost;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime openedAt;
}
