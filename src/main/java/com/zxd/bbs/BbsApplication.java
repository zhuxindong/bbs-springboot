package com.zxd.bbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableCaching
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 300)
@SpringBootApplication
public class BbsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BbsApplication.class, args);
    }

}
