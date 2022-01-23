/*
 * Copyright ShangYh
 */
package io.github.thinwind.bluedream.misc;

/**
*
* 常量集合
*
* @author ShangYh <niceshang@outlook.com>
* @since 2020-09-01 11:03
*
*/
public final class Consts {

    /**
     * 工具类的私有构造方法，防止被实例化
     */
    private Consts(){}

    /**
     * JPA的id生成器名称
     */
    public static final String ID_GENERATOR="BLUEDREAM_ID_GENERATOR";
    
    /**
     * 请求参数中TraceId的key
     */
    public static final String TRACE_ID_KEY = "_traceId";
    
    public static final String HOST_KEY = "bluedream.host";
    
    /**
     * 请求和响应Header中TraceId的名称
     */
    public static final String TRACE_ID_HEADER_KEY = "X-BlueDream-TraceId";

    public static final String CLIENT_IP_HEADER = "X-BlueDream-Client-IP";
}
