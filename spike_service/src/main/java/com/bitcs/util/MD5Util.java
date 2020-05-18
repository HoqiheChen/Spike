package com.bitcs.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 加密工具类
 *
 * @author GeChen
 */
public class MD5Util {
    private static final String salt = "bitcs1039";

    public static String md5(String raw) {
        return DigestUtils.md5Hex(raw);
    }

    /**
     * 将输入的明文密码加密为表单密码
     *
     * @param inputPassWord
     * @return
     */
    public static String inputPassToFormPass(String inputPassWord) {
        String string = "" + salt.charAt(0) + inputPassWord + salt.charAt(2) + salt.charAt(4);
        return md5(string);
    }

    /**
     * 将表单密码加密为数据库存储密码
     *
     * @param formPass
     * @param salt
     * @return
     */
    public static String formPassToDBPass(String formPass, String salt) {
        String string = "" + salt.charAt(0) + formPass + salt.charAt(2) + salt.charAt(4);
        return md5(string);
    }

    /**
     * 直接对输入密码两次加密获取数据库存储密码
     *
     * @param input
     * @param saltDB
     * @return
     */
    public static String inputPassToDBPass(String input, String saltDB) {
        String formPass = inputPassToFormPass(input);
        String dbPass = formPassToDBPass(formPass, saltDB);
        return dbPass;
    }
}
