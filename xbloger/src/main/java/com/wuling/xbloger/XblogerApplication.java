package com.wuling.xbloger;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication(scanBasePackages = "com.wuling.xbloger")
@MapperScan("com.wuling.xbloger.mapper")
@ServletComponentScan
public class XblogerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(XblogerApplication.class, args);
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(XblogerApplication.class);
//    }

}
