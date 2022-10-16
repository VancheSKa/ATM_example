package com.kaivse;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Ivan Kalinin 16.10.2022
 */
public class HmacUtils {
    private static final String ALGORITHM = "HmacSHA256";

    public static String calculateHmac(String key, String data) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac sha256_HMAC = Mac.getInstance(ALGORITHM);
        SecretKey secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        sha256_HMAC.init(secretKey);

        return byteArrayToHex(sha256_HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8)));
    }

    public static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for (byte b : a)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
