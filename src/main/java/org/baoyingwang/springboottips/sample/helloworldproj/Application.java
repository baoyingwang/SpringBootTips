package org.baoyingwang.springboottips.sample.helloworldproj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@EnableScheduling this is required if there are spring schedule jobs. but maybe jobs are used with some specific 3rd party tools, e.g. ??
@Configuration
//@EnableTransactionManagement
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}

