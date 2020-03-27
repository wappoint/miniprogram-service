package cn.net.uxon;

import cn.net.uxon.tools.ResponseResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.out.println(ResponseResult.success("hellworld"));
        SpringApplication.run(Application.class, args);
    }
}
