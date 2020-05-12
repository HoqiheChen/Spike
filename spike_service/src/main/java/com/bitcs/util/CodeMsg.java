package com.bitcs.util;

/**
 * 状态码和状态信息
 *
 * @author GeChen
 */
public class CodeMsg {
    /**
     * 状态码
     */
    private int code;
    /**
     * 状态信息
     */
    private String msg;

    //通用异常

    /**
     * 成功状态码
     */
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    /**
     * 服务端异常状态码
     */
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
