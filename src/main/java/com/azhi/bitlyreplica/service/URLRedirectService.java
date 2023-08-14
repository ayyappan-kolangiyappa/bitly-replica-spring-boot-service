package com.azhi.bitlyreplica.service;

public interface URLRedirectService {
    String getOriginalUrl(String encryptedId, String openFromHost);
}
