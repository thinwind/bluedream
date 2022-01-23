package io.github.thinwind.bluedream.config;

import java.lang.reflect.AnnotatedElement;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import io.github.thinwind.bluedream.anno.DoNotWrap;
import io.github.thinwind.bluedream.aware.ClientDataAware;
import io.github.thinwind.bluedream.dto.HttpResult;
import io.github.thinwind.bluedream.dto.PagedResult;
import io.github.thinwind.bluedream.misc.Consts;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * 统一返回结果处理
 *
 * @author ShangYh <niceshang@outlook.com>
 * @since 2020-09-01 15:38
 *
 */
@Slf4j
@RestControllerAdvice("io.github.thinwind.bluedream.controller")
public class CommonResultAdvice
        implements ResponseBodyAdvice<Object>, ClientDataAware{

    private final ObjectMapper objectMapper;
    
    private final EnvConfig envConfig;

    public CommonResultAdvice(ObjectMapper objectMapper,EnvConfig envConfig) {
        this.objectMapper = objectMapper;
        this.envConfig = envConfig;
    }

    @Override
    public boolean supports(MethodParameter returnType,
            Class<? extends HttpMessageConverter<?>> converterType) {
        AnnotatedElement annos = returnType.getAnnotatedElement();
        return !annos.isAnnotationPresent(DoNotWrap.class)
                && !ByteArrayHttpMessageConverter.class.isAssignableFrom(converterType);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
            MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof byte[]) {
            //不处理二进制文件
            return body;
        }

        HttpResult result = new HttpResult();
        result.setSuccess(true);
        String traceId = getTraceId();
        if (traceId == null) {
            traceId = response.getHeaders().get(Consts.TRACE_ID_HEADER_KEY).get(0);
        }
        result.setTraceId(traceId);
        result.setHost(envConfig.getHost());

        if (body instanceof PagedResult) {
            PagedResult<?> pr = (PagedResult<?>) body;
            result.setData(pr.getContent());
            result.setCurrentPage(pr.getCurrentPage());
            result.setTotal(pr.getTotal());
            result.setPageSize(pr.getPageSize());
        } else {
            result.setData(body);
        }

        if (body instanceof String) {
            //String仍然按照String处理
            try {
                if (response instanceof ServletServerHttpResponse) {
                    HttpServletResponse reps =
                            ((ServletServerHttpResponse) response).getServletResponse();
                    reps.setContentType("application/json");
                    reps.setCharacterEncoding("utf-8");
                }
                return objectMapper.writeValueAsString(result);
            } catch (JsonProcessingException e) {
                log.error("序列化结果错误", e);
                return body;
            }
        }

        return result;
    }
}
