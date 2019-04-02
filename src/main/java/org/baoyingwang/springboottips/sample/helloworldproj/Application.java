package org.baoyingwang.springboottips.sample.helloworldproj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableTransactionManagement
public class Application {

    public static void main(String[] args) {

        //TODO overall restful api sample
        System.out.println("TODO - high: refer other project api definition, e.g. wechat, etc to refine the restful interface, https://open.weixin.qq.com/");

        SpringApplication.run(Application.class);
    }
}

