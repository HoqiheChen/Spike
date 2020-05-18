package com.bitcs.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 格式校验工具类
 *
 * @author GeChen
 */
public class ValidatorUtil {
    /**
     * 手机号的匹配模式
     */
    private static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String string) {
        if (StringUtils.isEmpty(string)) {
            return false;
        }
        Matcher matcher = mobile_pattern.matcher(string);
        return matcher.matches();
    }
}
