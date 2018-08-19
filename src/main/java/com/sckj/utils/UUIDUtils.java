package com.sckj.utils;

import java.util.UUID;

public class UUIDUtils {
    public UUIDUtils() {
    }

    public static String generate() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void main(String[] args) throws Exception {
        for(int i = 0; i < 100; ++i) {
            String id = UUID.randomUUID().toString().replaceAll("-", "");
            System.out.println(id + "," + id.length());
        }

    }
}
