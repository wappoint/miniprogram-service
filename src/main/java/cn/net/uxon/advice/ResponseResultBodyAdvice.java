package cn.net.uxon.advice;

import cn.net.uxon.exception.ResultException;
import cn.net.uxon.tools.ResponseResult;
import cn.net.uxon.tools.ResultStatus;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.util.WebUtils;

import java.lang.annotation.Annotation;

@RestControllerAdvice
public class ResponseResultBodyAdvice implements ResponseBodyAdvice<Object> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());


    private static final Class<? extends Annotation> ANNOTATION_TYPE = ResponseResultBody.class;

    private static final ObjectMapper mapper = new ObjectMapper();


    /**
     * 判断类或者方法是否使用了 @ResponseResultBody
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> convertType) {
        LOGGER.info("invoke supports of the ResponseResultBodyAdvice ...");
        return AnnotatedElementUtils.hasAnnotation(methodParameter.getContainingClass(), ANNOTATION_TYPE) || methodParameter.hasMethodAnnotation(ANNOTATION_TYPE);
    }

    /**
     * 当类或者方法使用了 @ResponseResultBody 就会调用这个方法
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        LOGGER.info("invoke beforeBodyWrite of the ResponseResultBodyAdvice ... ");
        if (o instanceof ResponseResult) {
            return o;
        }
        if (o instanceof String) {
            try {
                return mapper.writeValueAsString(o);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return ResponseResult.success(o);
    }

    /**
     * 提供对标准Spring MVC异常的处理
     *
     * @param ex      the target exception
     * @param request the current request
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ResponseResult<?>> exceptionHandler(Exception ex, WebRequest request) {
        LOGGER.error("ExceptionHandler: {}", ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        if (ex instanceof ResultException) {
            return this.handleResultException((ResultException) ex, headers, request);
        }
        return this.handleException(ex, headers, request);
    }

    /**
     * 对ResultException类返回返回结果的处理
     */
    protected ResponseEntity<ResponseResult<?>> handleResultException(ResultException ex, HttpHeaders headers, WebRequest request) {
        LOGGER.error("invoke handleResultException of the ResponseResultBodyAdvice");
        ResponseResult<?> body = ResponseResult.failure(ex.getResultStatus());
        HttpStatus status = ex.getResultStatus().getHttpStatus();
        return this.handleExceptionInternal(ex, body, headers, status, request);
    }

    /**
     * 异常类的统一处理
     */
    protected ResponseEntity<ResponseResult<?>> handleException(Exception ex, HttpHeaders headers, WebRequest request) {
        LOGGER.error("invoke handleResultException of the handleException");
        ResponseResult<?> failure = ResponseResult.failure();
        return this.handleExceptionInternal(ex, failure, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    protected ResponseEntity<ResponseResult<?>> handleExceptionInternal(Exception ex, ResponseResult<?> result, HttpHeaders headers, HttpStatus status, WebRequest request) {
        LOGGER.error("invoke handleResultException of the handleException");
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(result, headers, status);
    }
}
