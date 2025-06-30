package com.shosha.ecommerce.service.util;

public class StringUtil {

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static String getString(Object o) {
        if (o == null) {
            return "";
        }
        return o.toString();
    }
}