package com.bitcs.util;

import java.util.UUID;

/**
 * @author GeChen
 */
public class UUIDUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
