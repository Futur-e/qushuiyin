package com.example.huafeng_serve.common.utils;

import java.util.Base64;

public class Base64Util {
    // 编码
    public static String encodeString(String input) {
        byte[] encodedBytes = Base64.getEncoder().encode(input.getBytes());
        return new String(encodedBytes);
    }

    // 解码
    public static String decodeString(String encodedInput) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedInput.getBytes());
        return new String(decodedBytes);
    }
}
