package io.github.thinwind.bluedream.exception;

import io.github.thinwind.bluedream.misc.Errors;
import lombok.Getter;

/**
*
* 通用异常
*
* @author ShangYh <niceshang@outlook.com>
* @since 2020-09-01 16:41
*
*/
public class BlueDreamException extends RuntimeException {

    /**
    *
    */
    private static final long serialVersionUID = -1420033772639101773L;

    @Getter
    private String errorCode;
    
    @Getter
    private String errorMessage;

    public BlueDreamException(String errorCode) {
        super(errorCode);
    }
    
    public BlueDreamException(Errors error) {
        this(error.getErrorCode(), error.getDefaultErrMsg());
    }

    public BlueDreamException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}