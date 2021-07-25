package com.woori.wooribackoffice.util;

public class CustomStringUtil {
    private static final char WHITE_SPACE = ' ';

    public static String removeAllWhiteSpace(String str) {
        if(str == null) {
            throw new IllegalArgumentException("Input String is NULL!");
        }

        String r = "";
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(c != WHITE_SPACE) {
                r += c;
            }
        }

        return r;
    }
}
