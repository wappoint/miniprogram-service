package cn.net.uxon.advice;

import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

@ResponseResultBody
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@ResponseBody
public @interface ResponseResultBody {
}
