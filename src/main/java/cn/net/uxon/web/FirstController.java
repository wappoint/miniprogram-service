package cn.net.uxon.web;

import cn.net.uxon.advice.ResponseResultBody;
import cn.net.uxon.exception.ResultException;
import cn.net.uxon.tools.ResponseResult;
import cn.net.uxon.tools.ResultStatus;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@ResponseResultBody
public class FirstController {

    private static final HashMap<String, Object> INFO;

    static {
        INFO = new HashMap<>();
        INFO.put("name", "galaxy");
        INFO.put("age", "70");
    }


    @GetMapping("/")
    @ApiOperation(value = "首页地址" , notes = "根路径地址")
    public String helloWorld() {
        return "hello";
    }


    @GetMapping("/hello")
    public Map<String, Object> hello() {
        return INFO;
    }

    @GetMapping("/result")
    public ResponseResult<Map<String, Object>> helloResult() {
        return ResponseResult.success(INFO);
    }

    /**
     * 测试重复包裹
     */
    @GetMapping("/object")
    public ResponseResult<Map<String, Object>> objectResult() {
        return ResponseResult.success(INFO);
    }

    @GetMapping("/helloError")
    public HashMap<String, Object> helloError() throws Exception {
        throw new ResultException(ResultStatus.BAD_REQUEST);
    }

    @GetMapping("helloMyError")
    public HashMap<String, Object> helloMyError() throws Exception {
        throw new MyException();
    }


    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "自己定义的异常")
    class MyException extends Exception {

    }

}
