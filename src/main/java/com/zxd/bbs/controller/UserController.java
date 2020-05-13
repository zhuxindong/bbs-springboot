package com.zxd.bbs.controller;

import com.google.code.kaptcha.Producer;
import com.zxd.bbs.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
public class UserController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Autowired
    Producer producer;


    @RequestMapping("/bbs")
    public String bbsInfo(){
        return "bbs use spring boot";
    }

    //图片验证码
    @RequestMapping("/captcha.jpg")
    public void captcha(HttpServletResponse response)throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text = producer.createText();
        System.out.println("验证码：" + text);
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }


    @RequestMapping("/redis")
    public String redis(){
        redisTemplate.opsForValue().set("hah","sss",30, TimeUnit.SECONDS);
        String a = redisTemplate.opsForValue().get("hah");
        System.out.println(a);
        return "bbs use spring boot";
    }



}
