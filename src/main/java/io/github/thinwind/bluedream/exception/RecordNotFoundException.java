package io.github.thinwind.bluedream.exception;

import io.github.thinwind.bluedream.misc.Errors;

/**
 *
 * 记录不存在时抛出此异常
 *
 * @author ShangYh <niceshang@outlook.com>
 * @since 2020-09-02 14:25
 *
 */
public class RecordNotFoundException extends BlueDreamException {

    /**
    *
    */
    private static final long serialVersionUID = 9022631989232376367L;
    
    public RecordNotFoundException(Errors error){
        super(error);
    }

    public RecordNotFoundException(String errorCode) {
        super(errorCode);
    }

    public RecordNotFoundException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

}
