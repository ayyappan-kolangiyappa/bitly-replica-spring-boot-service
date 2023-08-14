package com.azhi.bitlyreplica.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.stream.IntStream;

@Component
public class RandomStringGenerator {
    private static final String ALPHA_NUMERIC = "Aa1Bb2Cc3Dd4Ee5Ff6Gg7Hh8Ii9Jj0KkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
    private final SecureRandom secureRandom;


    public RandomStringGenerator() throws NoSuchAlgorithmException {
        secureRandom = SecureRandom.getInstance("NativePRNG");
    }

    public String randomStringGenerator(long randomStringSize) {
        IntStream intStream = secureRandom.ints(randomStringSize, 0, 62);
        StringBuilder stringBuilder = new StringBuilder();
        intStream.mapToObj(ALPHA_NUMERIC::charAt)
                .forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

}
