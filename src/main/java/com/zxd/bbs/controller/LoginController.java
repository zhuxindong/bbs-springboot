package com.zxd.bbs.controller;

import com.zxd.bbs.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "index.html";
    }

    @RequestMapping("/login")
    public String userLogin(User user, Model model){

        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(), user.getPassword());

        System.out.println(user.getUsername());

        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
        } catch (AuthenticationException e) {

//            model.addAttribute("msg","账号或密码错误！");
            return "redirect:index.html";
        } catch (AuthorizationException e) {

            model.addAttribute("msg","没有权限！");
            return "redirect:index.html";
        }  catch (Exception e){
            e.printStackTrace();
            model.addAttribute("msg","账号或密码错误！");
            return "redirect:index.html";
        }

        return "redirect:user.html";

    }


    @RequestMapping("/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "redirect:index.html";
    }

}
