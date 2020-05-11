package com.wuling.xbloger;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wuling.xbloger.mapper")
public class XblogerApplication {

    public static void main(String[] args) {
        SpringApplication.run(XblogerApplication.class, args);
    }

}
