package me.zhengjie;

import io.swagger.annotations.Api;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(hidden = true)
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.gitee.coadmin", "me.zhengjie"})
@MapperScan("com.gitee.coadmin.*.*.service.mapper")
public class WechatRun {

    public static void main(String[] args) {
        SpringApplication.run(WechatRun.class,args);
    }
}
