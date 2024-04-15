package com.example.huafeng_serve.common.utils;

public class PathUtil {
    public static String getExtractDomain(String path) {
        String[] domainSuffixes = {
                ".com",
                ".net",
                ".org",
                ".edu",
                ".gov",
                ".mil",
                ".info",
                ".biz",
                ".co",
                ".io",
                ".me",
                ".cn"
        };

        for (int i = 0; i < domainSuffixes.length; i++) {
            if (path.contains(domainSuffixes[i])) {
                return path.substring(0, path.indexOf(domainSuffixes[i]) + domainSuffixes[i].length());
            }
        }
        return null;
    }
}
