package io.github.thinwind.bluedream.misc;

import lombok.Getter;

/**
 *
 * 错误信息
 *
 * @author ShangYh <niceshang@outlook.com>
 * @since 2020-09-02 11:26
 *
 */
@Getter
public enum Errors {
    /**
     * 入参错误
     */
    INPUT_PARAM_ERROR("IP004", "入参错误"),
    /**
     * 网络错误
     */
    NET_WORK_ERROR("NT004", "网络错误"),
    /**
     * 数据库不可用
     */
    DATABASE_NOT_AVAILABLE("DB004", "数据库不可用"),
    /**
     * 未知原因导致服务器错误
     */
    SERVER_ERROR("SE500", "服务器错误"), 
    /**
     * 查询或者修改的参数没有找到
     */
    RECORD_NOT_FOUND("NF004", "参数未找到"),
    /**
     * 输出结果时发生错误，比如存在循环引用导致的json序列化失败
     */
    OUTPUT_RESULT_ERROR("OP004","输出结果错误")
    ;

    private final String errorCode;
    private final String defaultErrMsg;

    private Errors(String errorCode, String defaultErrMsg) {
        this.errorCode = errorCode;
        this.defaultErrMsg = defaultErrMsg;
    }

}
