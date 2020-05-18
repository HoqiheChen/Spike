package com.bitcs.exception;

import com.bitcs.util.CodeMsg;

/**
 * 全局异常
 *
 * @author GeChen
 */
public class GlobalException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private CodeMsg codeMsg;

    public GlobalException(CodeMsg codeMsg) {
        super(codeMsg.toString());
        this.codeMsg = codeMsg;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }
}
